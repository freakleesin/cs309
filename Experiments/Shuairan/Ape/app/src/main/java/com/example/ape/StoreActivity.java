package com.example.ape;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

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
 * show store information, average score, comments
 */
public class StoreActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    String merchantsid = "";
    TextView storecoms1,storecoms2,storecoms3,storecoms4,storecoms5,sscore;
    ImageView iservice;
    TextView sname, mbusiness, saddress, comments;
    Button bcommunity,bhome,bperson,brating;

    String server_url = "http://192.168.56.1:8888/merchants/getMerchantsByUsersId";
    String server_url_cp2 = "http://192.168.56.1:8888/commentsStars/listCommentsStarsByMerchantsId";

    //String server_url = "http://coms-309-hv-5.cs.iastate.edu:8888/merchants/getMerchantsByUsersId";
    //String server_url_cp2 = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/listCommentsVOByMerchantsId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_site);
        Log.d(TAG, "onCreate: ");

        sname = findViewById(R.id.Store_Name);
        sscore = findViewById(R.id.scores);
        mbusiness = findViewById(R.id.Main_Business);
        saddress = findViewById(R.id.Store_Address);
        //comments = findViewById(R.id.comments);

        storecoms1 = findViewById(R.id.textView8);
        storecoms2 = findViewById(R.id.textView9);
        storecoms3 = findViewById(R.id.textView10);
        storecoms4 = findViewById(R.id.textView11);
        storecoms5 = findViewById(R.id.textView12);

        iservice = findViewById(R.id.Comsuer_Picture);
        brating = findViewById(R.id.Store_Site_Rating);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String mid = intent.getStringExtra("mid");
        final String storename = intent.getStringExtra("storename");
        final String storescore = intent.getStringExtra("store_score");
        final String username = intent.getStringExtra("username");
        sname.setText(storename);
        sscore.setText(storescore);

        /**
         * get store main business, store address, users comments
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.optString("data");
                    JSONObject jdata = new JSONObject(data);
                    String mainbusiness = jdata.optString("mainBusiness");
                    String storeaddress = jdata.optString("address");
                    String merchantsId = jdata.optString("id");
                    mbusiness.setText(mainbusiness);
                    saddress.setText(storeaddress);
                    merchantsid = merchantsId;

                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, server_url_cp2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);

                                JSONArray arr = jsonObject.getJSONArray("data");
                                JSONObject jo = null;
                                List datalist = new ArrayList();
                                for(int i = 0; i < arr.length(); i++){
                                    jo = arr.getJSONObject(i);
                                    datalist.add(arr.get(i).toString());
                                }
                                for(int i=0; i < datalist.size(); i++){
                                    JSONObject jdata = new JSONObject((String) datalist.get(i));
                                    final String merchscomts = jdata.optString("content");
                                    if(i == 0){
                                        storecoms1.setText(merchscomts);
                                    }else if(i == 1){
                                        storecoms2.setText(merchscomts);
                                    }else if(i == 2){
                                        storecoms3.setText(merchscomts);
                                    }else if(i == 3){
                                        storecoms4.setText(merchscomts);
                                    }else if(i == 4){
                                        storecoms5.setText(merchscomts);
                                    }
                                    /*
                                    @SuppressLint("InflateParams") View view = LayoutInflater.from(StoreActivity.this).inflate(R.layout.store_site,null);
                                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.addcomments);
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                                    TextView valueView = new TextView(StoreActivity.this);
                                    valueView.setText(merchscomts);
                                    valueView.setLayoutParams(layoutParams);
                                    linearLayout.addView(valueView);

                                     */
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(StoreActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(StoreActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("merchantsId",merchantsid);
                            return params;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(stringRequest1);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(StoreActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StoreActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("usersId", mid);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

        iservice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, ChatRoomTest.class);
                intent.putExtra("id",id);
                intent.putExtra("chatname",storename);
                intent.putExtra("chatsId",merchantsid);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        brating.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, RatingActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("storename",storename);
                intent.putExtra("merchantsId",merchantsid);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, StoreCommunityActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        bhome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, StoreHomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        bperson.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, StoreCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

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