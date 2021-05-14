package com.example.ape;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ape.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuairan Chen
 * show the same merchants type store list
 */
public class StoreFriendsActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    String chatname, chatsId; // fi(n), fs(n)
    String fs1, fs2, fs3, fs4, fs5, fs6, fs7, fs8, fs9;
    TextView fun1,fun2,fun3,fun4,fun5,fun6,fun7,fun8,fun9, fsc1, fsc2, fsc3, fsc4, fsc5,fsc6, fsc7, fsc8, fsc9;
    ImageView fi1, fi2, fi3, fi4, fi5, fi6, fi7, fi8, fi9, addf, like1,like2,like3,like4,like5,like6,like7,like8,like9;
    Button bcommunity,bhome,bperson;

    String server_url_cp = "http://192.168.56.1:8888/friends/listUsersVOOffriendsModelByUsersId"; //getname
    String server_url_cp2 = "http://192.168.56.1:8888/users/get";
    String server_url_cp3 = "http://192.168.56.1:8888/users/like";

    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/merchants/listMerchantsVOByType"; //getname
    //String server_url_cp2 = "http://coms-309-hv-5.cs.iastate.edu:8888/users/get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_friends_site);
        Log.d(TAG, "onCreate: ");

        fun1 = findViewById(R.id.fn01);
        fun2 = findViewById(R.id.fn02);
        fun3 = findViewById(R.id.fn03);
        fun4 = findViewById(R.id.fn04);
        fun5 = findViewById(R.id.fn05);
        fun6 = findViewById(R.id.fn06);
        fun7= findViewById(R.id.fn07);
        fun8 = findViewById(R.id.fn08);
        fun9 = findViewById(R.id.fn09);

        fsc1 = findViewById(R.id.likenum01);
        fsc2 = findViewById(R.id.likenum02);
        fsc3 = findViewById(R.id.likenum03);
        fsc4 = findViewById(R.id.likenum04);
        fsc5 = findViewById(R.id.likenum05);
        fsc6 = findViewById(R.id.likenum06);
        fsc7 = findViewById(R.id.likenum07);
        fsc8 = findViewById(R.id.likenum08);
        fsc9 = findViewById(R.id.likenum09);

        fi1 = findViewById(R.id.ficon01);
        fi2 = findViewById(R.id.ficon02);
        fi3 = findViewById(R.id.ficon03);
        fi4 = findViewById(R.id.ficon04);
        fi5 = findViewById(R.id.ficon05);
        fi6 = findViewById(R.id.ficon06);
        fi7 = findViewById(R.id.ficon07);
        fi8 = findViewById(R.id.ficon08);
        fi9 = findViewById(R.id.ficon09);

        like1 = findViewById(R.id.like01);
        like2 = findViewById(R.id.like02);
        like3 = findViewById(R.id.like03);
        like4 = findViewById(R.id.like04);
        like5 = findViewById(R.id.like05);
        like6 = findViewById(R.id.like06);
        like7 = findViewById(R.id.like07);
        like8 = findViewById(R.id.like08);
        like9 = findViewById(R.id.like09);

        addf = findViewById(R.id.add_friends);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        updatefriendlist(id,server_url_cp);

        like1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun1.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun2.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun3.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun4.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun5.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun6.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun7.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun8.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });

        like9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String likeusername = (String) fun9.getText();
                updatelike(likeusername, server_url_cp3);
                updatefriendlist(id, server_url_cp);
            }
        });


        fun1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun1.getText());
                intent.putExtra("chatsId", fi1.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun2.getText());
                intent.putExtra("chatsId", fi2.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun3.getText());
                intent.putExtra("chatsId", fi3.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun4.getText());
                intent.putExtra("chatsId", fi4.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun5.getText());
                intent.putExtra("chatsId", fi5.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun6.getText());
                intent.putExtra("chatsId", fi6.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun7.getText());
                intent.putExtra("chatsId", fi7.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun8.getText());
                intent.putExtra("chatsId", fi8.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        fun9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",fun9.getText());
                intent.putExtra("chatsId", fi9.toString());
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, StoreCommunityActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to home site
         */
        bhome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, StoreHomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to personal center
         */
        bperson.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, StoreCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to add friends site
         */
        addf.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreFriendsActivity.this, StoreAddFriendsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    /**
     * get same type merchants list
     */
    public void updatefriendlist(final String id, String url1) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray arr = jsonObject.getJSONArray("data");
                    JSONObject jo = null;
                    List datalist = new ArrayList();
                    for (int i = 0; i < arr.length(); i++) {
                        jo = arr.getJSONObject(i);
                        datalist.add(arr.get(i).toString());
                    }
                    for (int i = 0; i < datalist.size(); i++) {
                        JSONObject jdata = new JSONObject((String) datalist.get(i));
                        final String funame = jdata.optString("username");
                        final String fid = jdata.optString("usersId");
                        final String mscore = jdata.optString("likeNum");
                        final int index = i;
                        if (index == 0) {
                            fun1.setText(funame);
                            fs1 = fid;
                            fsc1.setText(mscore);
                            fi1.setImageResource(R.drawable.defultavatar);
                            like1.setImageResource(R.drawable.likeicon);
                        } else if (index == 1) {
                            fun2.setText(funame);
                            fs2 = fid;
                            fsc2.setText(mscore);
                            fi2.setImageResource(R.drawable.defultavatar);
                            like2.setImageResource(R.drawable.likeicon);
                        } else if (index == 2) {
                            fun3.setText(funame);
                            fs3 = fid;
                            fsc3.setText(mscore);
                            fi3.setImageResource(R.drawable.defultavatar);
                            like3.setImageResource(R.drawable.likeicon);
                        } else if (index == 3) {
                            fun4.setText(funame);
                            fs4 = fid;
                            fsc4.setText(mscore);
                            fi4.setImageResource(R.drawable.defultavatar);
                            like4.setImageResource(R.drawable.likeicon);
                        } else if (index == 4) {
                            fun5.setText(funame);
                            fs5 = fid;
                            fsc5.setText(mscore);
                            fi5.setImageResource(R.drawable.defultavatar);
                            like5.setImageResource(R.drawable.likeicon);
                        } else if (index == 5) {
                            fun6.setText(funame);
                            fs6 = fid;
                            fsc6.setText(mscore);
                            fi6.setImageResource(R.drawable.defultavatar);
                            like6.setImageResource(R.drawable.likeicon);
                        } else if (index == 6) {
                            fun7.setText(funame);
                            fs7 = fid;
                            fsc7.setText(mscore);
                            fi7.setImageResource(R.drawable.defultavatar);
                            like7.setImageResource(R.drawable.likeicon);
                        } else if (index == 7) {
                            fun8.setText(funame);
                            fs8 = fid;
                            fsc8.setText(mscore);
                            fi8.setImageResource(R.drawable.defultavatar);
                            like8.setImageResource(R.drawable.likeicon);
                        } else if (index == 8) {
                            fun9.setText(funame);
                            fs9 = fid;
                            fsc9.setText(mscore);
                            fi9.setImageResource(R.drawable.defultavatar);
                            like9.setImageResource(R.drawable.likeicon);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(StoreFriendsActivity.this, "No Response!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StoreFriendsActivity.this, "Response Error!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usersId", id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void updatelike(final String username, String url3){
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.optString("status");
                    if (code.equals("fail")) {
                        Toast.makeText(StoreFriendsActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(StoreFriendsActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StoreFriendsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest1);
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
