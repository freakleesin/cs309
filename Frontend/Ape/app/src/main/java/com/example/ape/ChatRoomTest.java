package com.example.ape;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ape.utils.Util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatRoomTest extends AppCompatActivity {
    private WebSocket webSocket;
    TextView sname;
    EditText messageBox;
    Button bsend,bcommunity,bhome,bperson;
    String id, storename, merchantsid, username;
    ListView messageslist;
    MessageAdapter madapter = new MessageAdapter(ChatRoomTest.this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chating_site);

        sname = findViewById(R.id.chat_person);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);
        messageslist = findViewById(R.id.messages_view);
        messageBox = findViewById(R.id.chating_input_message);
        bsend = findViewById(R.id.chating_message_send);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        merchantsid = intent.getStringExtra("chatsId");
        storename = intent.getStringExtra("chatname");
        username = intent.getStringExtra("username");

        /*
        final MemberData sendmember = new MemberData(username, Integer.parseInt(id));
        final MemberData receivemember = new MemberData(storename, Integer.parseInt(merchantsid));
         */

        sname.setText(storename);

        initWebSocket();

        messageslist.setAdapter(madapter);

        bsend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String message = messageBox.getText().toString();
                if(!message.isEmpty()){
                    webSocket.send(message);
                    messageBox.setText("");
                    ChatMessage chatMessage = new ChatMessage(message, username, true);
                    madapter.add(chatMessage);
                }
            }
        });

    }

    private void initWebSocket(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://192.168.56.1:8888/chat/" + username).build();
        //Request request = new Request.Builder().url(Util.ws).build();
        SocketListener socketListener = new SocketListener(this);
        webSocket = client.newWebSocket(request, socketListener);
    }


    public class SocketListener extends WebSocketListener{
        public ChatRoomTest chatactivity;

        public SocketListener(ChatRoomTest activity){
            this.chatactivity = activity;
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response){
            super.onOpen(webSocket, response);

            chatactivity.runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    Toast.makeText(chatactivity, "Connection Success!", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onMessage(WebSocket webSocket, final String text){
            super.onMessage(webSocket, text);

            chatactivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ChatMessage chatMessage = new ChatMessage(text, storename, false);
                    madapter.add(chatMessage);
                }
            });
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason){
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response){
            super.onFailure(webSocket, t, response);
        }
    }

}
