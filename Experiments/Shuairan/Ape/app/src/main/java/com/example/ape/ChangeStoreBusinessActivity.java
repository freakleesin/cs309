package com.example.ape;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ape.MainActivity;
import com.example.ape.R;
import com.example.ape.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuairan Chen
 * merchants users change store main business
 */
public class ChangeStoreBusinessActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    Button bsubmit,bcommunity,bhome,bstore;
    TextView cstorename,mainbusiness;
    //AlertDialog.Builder builder;
    String server_url_cp = "http://192.168.56.1:8888/merchants/updateMerchantsMainBusiness";
    String server_url_cp2 = "http://192.168.56.1:8888/users/get"; //getname
    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/merchants/updateMerchantsMainBusiness";
    //String server_url_cp2 = "http://coms-309-hv-5.cs.iastate.edu:8888/users/get"; //getname
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_store_business);
        Log.d(TAG, "onCreate: ");

        cstorename = findViewById(R.id.nowstorename);
        mainbusiness = findViewById(R.id.mainbusiness);

        bsubmit = findViewById(R.id.Username_Submit);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bstore = findViewById(R.id.STORECENTER);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * get store name
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_cp2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.optString("data");
                    JSONObject jdata = new JSONObject(data);
                    String uname = jdata.optString("username");
                    cstorename.setText("");
                    cstorename.append(uname);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ChangeStoreBusinessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChangeStoreBusinessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("id",id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

        /**
         * submit new store main business
         */
        bsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String mbusiness = mainbusiness.getText().toString();
                    if(!mbusiness.isEmpty()) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_cp, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String code = jsonObject.optString("status");
                                    if (code.equals("fail")) {
                                        Toast.makeText(ChangeStoreBusinessActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                                        clearinformation();
                                    } else{
                                        Toast.makeText(ChangeStoreBusinessActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                        clearinformation();
                                        Intent intent = new Intent(ChangeStoreBusinessActivity.this, StoreCenterActivity.class);
                                        intent.putExtra("id",id);
                                        intent.putExtra("username", username);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ChangeStoreBusinessActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("usersId", id);
                                params.put("mainBusiness", mbusiness);
                                return params;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(stringRequest);
                    }else{
                        Toast.makeText(ChangeStoreBusinessActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                        clearinformation();
                    }
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChangeStoreBusinessActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(ChangeStoreBusinessActivity.this, StoreHomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to store center
         */
        bstore.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChangeStoreBusinessActivity.this, StoreCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    /**
     * clear input information
     */
    public void clearinformation(){
        //cstorename.setText("");
        mainbusiness.setText("");
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