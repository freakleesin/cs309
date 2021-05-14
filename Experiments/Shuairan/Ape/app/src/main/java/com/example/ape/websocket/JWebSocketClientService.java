package com.example.ape.websocekt;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
//import android.app.Service;
import android.app.Service;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.example.ape.ChatingActivitytest;
import com.example.ape.R;
import com.example.ape.utils.Util;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JWebSocketClientService extends Service {
    public JWebSocketClient client;
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();
    private final static int GRAY_SERVICE_ID = 1001;
    //promise activity
    public class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public final IBinder onBind(Intent intent) {
            return null;
        }

    }
    PowerManager.WakeLock wakeLock;//wake lock

    //
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock()
    {
        if (null == wakeLock)
        {
            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock)
            {
                wakeLock.acquire();
            }
        }
    }

    //binder activity and service
    public class JWebSocketClientBinder extends Binder {
        public JWebSocketClientService getService() {
            return JWebSocketClientService.this;
        }
    }

    /*
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

     */

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //initial websocket
        initSocketClient();
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//open heart beat

        if (Build.VERSION.SDK_INT < 18) {
            //Android4.3 lower hide notification icon
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else if(Build.VERSION.SDK_INT>18 && Build.VERSION.SDK_INT<25){
            //Android4.3 - Android7.0，hide notification icon
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setChannelId("notification_id");
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("notification_id", "notification_name", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
            startForeground(GRAY_SERVICE_ID, builder.build());
        }else{
            //Android7.0 upper show "running"
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        acquireWakeLock();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        closeConnect();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public JWebSocketClientService() {
    }


    /**
     * initial websocket connect
     */
    private void initSocketClient() {
        URI uri = URI.create(Util.ws);
        client = new JWebSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                Log.e("JWebSocketClientService", "Receive Message：" + message);

                Intent intent = new Intent();
                intent.setAction("com.xch.servicecallback.content");
                intent.putExtra("message", message);
                sendBroadcast(intent);

                checkLockAndShowNotification(message);
            }

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                super.onOpen(handshakedata);
                Log.e("JWebSocketClientService", "websocket connect success");
            }
        };
        connect();
    }

    /**
     * connect websocket
     */
    private void connect() {
        new Thread() {
            @Override
            public void run() {
                try {
                    //connectBlocking, try connect first, if connect false catch the error
                    client.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * send message
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        if (null != client) {
            Log.e("JWebSocketClientService", "Send Message:" + msg);
            client.send(msg);
        }
    }

    /**
     * disconnect
     */
    private void closeConnect() {
        try {
            if (null != client) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }


    /**
     * check screen lock or not
     *
     * @param content
     */
    private void checkLockAndShowNotification(String content) {
        //the service of management screen lock
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (km.inKeyguardRestrictedInputMode()) {//screen lock
            //get object which is power manager
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (!pm.isScreenOn()) {
                @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
                wl.acquire();  //light screen
                wl.release();  //after finish, release
            }
            sendNotification(content);
        } else {
            sendNotification(content);
        }
    }

    /**
     * send alert
     *
     * @param content
     */
    private void sendNotification(String content) {
        Intent intent = new Intent();
        intent.setClass(this, ChatingActivitytest.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_id", "notification_name", NotificationManager.IMPORTANCE_LOW);
            notifyManager.createNotificationChannel(channel);

            builder.setChannelId("notification_id");
        }

        Notification notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                //set priority
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.drawable.defultavatar)
                .setContentTitle("Service")
                .setContentText(content)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setWhen(System.currentTimeMillis())
                //add sound, shining light, and shock
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .build();
        notifyManager.notify(1, notification);//id should be different
    }


    //websocket heart beat test
    private static final long HEART_BEAT_RATE = 10 * 1000;//every 10 seconds check the connect
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("JWebSocketClientService", "Check websocket condition");
            if (client != null) {
                if (client.isClosed()) {
                    reconnectWs();
                }
            } else {
                //if client is null, initialize
                client = null;
                initSocketClient();
            }
            //perform heartbeat detection on long connections at regular intervals
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    /**
     * reconnect websocket
     */
    private void reconnectWs() {
        mHandler.removeCallbacks(heartBeatRunnable);
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.e("JWebSocketClientService", "Reconnect");
                    client.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
