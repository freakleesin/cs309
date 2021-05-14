package com.example.ape;
        import android.annotation.TargetApi;
        import android.app.AppOpsManager;
        import android.content.BroadcastReceiver;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.IntentFilter;
        import android.content.ServiceConnection;
        import android.content.pm.ApplicationInfo;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.IBinder;
        import android.provider.Settings;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.*;
        import android.content.Intent;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import com.example.ape.utils.Util;

        import java.lang.reflect.Field;
        import java.lang.reflect.Method;
        import java.util.ArrayList;
        import java.util.List;

        import com.example.ape.websocekt.JWebSocketClient;
        import com.example.ape.websocekt.JWebSocketClientService;

/**
 * @author Shuairan Chen
 * use to chating between normal users and merchants users
 */
public class ChatingActivitytest extends AppCompatActivity {
    private Context mContext;
    private JWebSocketClient client;
    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClientService jWebSClientService;
    private List<ChatMessage> chatMessageList = new ArrayList<>();
    private MessageAdapter adapter_chatMessage;
    private ListView listView;
    String TAG = "MyTAG";
    String id, storename, merchantsid, username;
    TextView sname, umessage;
    Button bsend,bcommunity,bhome,bperson;
    //AlertDialog.Builder builder;
    //String server_url_cp = "http://192.168.56.1:8888/comments/newComments";
    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/newComments";

    private ServiceConnection serviceConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("ChatingActivity", "Service connect activity success!");
            binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("ChatingActivity", "Service disconnect activity success!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chating_site);
        Log.d(TAG, "onCreate: ");
        mContext = ChatingActivitytest.this;
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        merchantsid = intent.getStringExtra("chatsId");
        storename = intent.getStringExtra("chatname");
        username = intent.getStringExtra("username");
        sname.setText(storename);
        findViewById();
        //start service
        startJWebSClientService();
        bindService();
        doRegisterReceiver();
        initView();

        /*
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChatingActivity.this, CommunityActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        bhome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChatingActivity.this, HomeActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        bperson.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChatingActivity.this, PersonalCenterActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

         */

    }

    /**
     * clear input information
     */
    public void clearinformation(){
        umessage.setText("");
    }

    /*
    @Override
    public void onMessage(Room room, final JsonNode json, final String username, final int userId) {
        // To transform the raw JsonNode into a POJO we can use an ObjectMapper
        final ObjectMapper mapper = new ObjectMapper();
        try {
            // member.clientData is a MemberData object, let's parse it as such
            final MemberData data = new MemberData(username, userId);
            // if the clientID of the message sender is the same as our's it was sent by us
            boolean belongsToCurrentUser = receivedMessage.getClientID().equals(scaledrone.getClientID());
            // since the message body is a simple string in our case we can use json.asText() to parse it as such
            // if it was instead an object we could use a similar pattern to data parsing
            final Message message = new Message(receivedMessage.getData().asText(), data, belongsToCurrentUser);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    // scroll the ListView to the last added element
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
     */

    /**
     * start sebsocket service
     */
    private void startJWebSClientService() {
        Intent intent = new Intent(mContext, JWebSocketClientService.class);
        startService(intent);
    }

    /**
     * bind service
     */
    private void bindService() {
        Intent bindIntent = new Intent(mContext, JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    private class ChatMessageReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            MemberData memberData = new MemberData(storename, Integer.parseInt(merchantsid));
            ChatMessage chatMessage=new ChatMessage(message, storename, false);
            chatMessageList.add(chatMessage);
            initChatMsgListView();
        }
    }

    /*
    private class ChatMessageReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            String message = intent.getStringExtra("");
        }
    }

     */

    private void initChatMsgListView(){
        adapter_chatMessage = new MessageAdapter(ChatingActivitytest.this);
        adapter_chatMessage.add((ChatMessage) chatMessageList);
        listView.setAdapter(adapter_chatMessage);
        listView.setSelection(chatMessageList.size());
    }

    /**
     * register receiver
     */
    private void doRegisterReceiver(){
        ChatMessageReceiver chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter = new IntentFilter("com.xch.servicecallback.content");
        registerReceiver(chatMessageReceiver, filter);
    }

    private void findViewById() {
        sname = findViewById(R.id.chat_person);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);
        listView = findViewById(R.id.messages_view);
        bsend = findViewById(R.id.chating_message_send);
        umessage = findViewById(R.id.chating_input_message);
        bsend.setOnClickListener((View.OnClickListener) this);
    }

    private void initView() {
        //Monitor input message changes
        umessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (umessage.getText().toString().length() > 0) {
                    bsend.setVisibility(View.VISIBLE);
                } else {
                    bsend.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chating_message_send:
                String content = umessage.getText().toString();
                if (content.length() <= 0) {
                    Util.showToast(mContext, "message could not be empty");
                    return;
                }

                if (client != null && client.isOpen()) {
                    jWebSClientService.sendMsg(content);
                    MemberData memberData = new MemberData(username,Integer.parseInt(id));
                    ChatMessage chatMessage = new ChatMessage(content, username, true);
                    chatMessageList.add(chatMessage);
                    initChatMsgListView();
                    clearinformation();
                } else {
                    Util.showToast(mContext, "Disconnected, please wait or restart the app.");
                }
                break;
            case R.id.COMMUNITY:
                Intent intentc = new Intent(ChatingActivitytest.this, CommunityActivity.class);
                intentc.putExtra("id",id);
                intentc.putExtra("username", username);
                startActivity(intentc);
            case R.id.PERSONAL:
                Intent intentp = new Intent(ChatingActivitytest.this, PersonalCenterActivity.class);
                intentp.putExtra("id",id);
                intentp.putExtra("username", username);
                startActivity(intentp);
            case R.id.HOME:
                Intent intenth = new Intent(ChatingActivitytest.this, HomeActivity.class);
                intenth.putExtra("id",id);
                intenth.putExtra("username", username);
                startActivity(intenth);
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}