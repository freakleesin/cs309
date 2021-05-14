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
public class RessActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    String si1, si2, si3, si4, si5, ssc1, ssc2, ssc3, ssc4,ssc5;
    TextView storetitle,store1,store2,store3,store4,store5, sscore1, sscore2, sscore3, sscore4, sscore5;
    Button bcommunity,bhome,bperson;

    String server_url_cp = "http://192.168.56.1:8888/merchants/listMerchantsVOByType"; //getname
    String server_url_cp2 = "http://192.168.56.1:8888/users/get";

    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/merchants/listMerchantsVOByType"; //getname
    //String server_url_cp2 = "http://coms-309-hv-5.cs.iastate.edu:8888/users/get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_sumsite);
        Log.d(TAG, "onCreate: ");

        storetitle = findViewById(R.id.MerchantsTitle);

        store1 = findViewById(R.id.Restaurants1);
        store2 = findViewById(R.id.Restaurants2);
        store3 = findViewById(R.id.Restaurants3);
        store4 = findViewById(R.id.Restaurants4);
        store5 = findViewById(R.id.Restaurants5);

        sscore1 = findViewById(R.id.sscore1);
        sscore2 = findViewById(R.id.sscore2);
        sscore3 = findViewById(R.id.sscore3);
        sscore4 = findViewById(R.id.sscore4);
        sscore5 = findViewById(R.id.sscore5);

        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String storetype = intent.getStringExtra("storetype");
        final String username = intent.getStringExtra("username");
        storetitle.setText(storetype);

        /**
         * get same type merchants list
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_cp, new Response.Listener<String>() {
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
                        final String merchid = jdata.optString("usersId");
                        final String mscore = jdata.optString("stars");
                        final int index = i;
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_cp2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.optString("data");
                                    JSONObject jdata = new JSONObject(data);
                                    String uname = jdata.optString("username");
                                    String mid = jdata.optString("id");
                                    if(index == 0){
                                        store1.setText(uname);
                                        si1 = mid;
                                        ssc1 = mscore;
                                        sscore1.setText(mscore);
                                    }else if(index == 1){
                                        store2.setText(uname);
                                        si2 = mid;
                                        ssc2 = mscore;
                                        sscore2.setText(mscore);
                                    }else if(index == 2){
                                        store3.setText(uname);
                                        si3 = mid;
                                        ssc3 = mscore;
                                        sscore3.setText(mscore);
                                    }else if(index == 3){
                                        store4.setText(uname);
                                        si4 = mid;
                                        ssc4 = mscore;
                                        sscore4.setText(mscore);
                                    }else if(index == 4){
                                        store5.setText(uname);
                                        si5 = mid;
                                        ssc5 = mscore;
                                        sscore5.setText(mscore);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(RessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String,String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<String,String>();
                                params.put("id",merchid);
                                return params;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(stringRequest);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("type",storetype);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

        /**
         * turn to store information site
         */
        store1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RessActivity.this, StoreActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("storename",store1.getText());
                intent.putExtra("mid", si1);
                intent.putExtra("store_score", ssc1);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        store2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RessActivity.this, StoreActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("storename",store2.getText());
                intent.putExtra("mid", si2);
                intent.putExtra("store_score", ssc2);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        store3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RessActivity.this, StoreActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("storename",store3.getText());
                intent.putExtra("mid", si3);
                intent.putExtra("store_score", ssc3);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        store4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RessActivity.this, StoreActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("storename",store4.getText());
                intent.putExtra("mid", si4);
                intent.putExtra("store_score", ssc4);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        store5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RessActivity.this, StoreActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("storename",store5.getText());
                intent.putExtra("mid", si5);
                intent.putExtra("store_score", ssc5);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RessActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(RessActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(RessActivity.this, PersonalCenterActivity.class);
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
