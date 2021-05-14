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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuairan Chen
 * show store cooperation menu
 */
public class StoreCenterActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    ImageView commu_icon;
    TextView tstorename;
    Button bstorename,bstorepassword,bstoreavatar,bmainbusiness,bstoreaddress, blogout,bcommunity,bhome,bstore;
    String server_url = "http://192.168.56.1:8888/users/get";
    //String server_url = "http://coms-309-hv-5.cs.iastate.edu:8888/users/get";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_center);
        Log.d(TAG, "onCreate: ");

        tstorename = findViewById(R.id.Storename);

        bstorename = findViewById(R.id.ChangeSN);
        bstorepassword = findViewById(R.id.ChangeSP);
        bstoreavatar = findViewById(R.id.ChangeSA);
        bmainbusiness = findViewById(R.id.ChangeMB);
        bstoreaddress = findViewById(R.id.ChangeAddress);
        blogout = findViewById(R.id.ChangeStoreType);

        commu_icon = findViewById(R.id.Communication_List);

        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bstore = findViewById(R.id.STORECENTER);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * get merchants name
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.optString("data");
                    JSONObject jdata = new JSONObject(data);
                    String uname = jdata.optString("username");
                    tstorename.setText("");
                    tstorename.append(uname);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(StoreCenterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StoreCenterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
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
         * log out
         */
        blogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, ChangeStoreTypeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to change merchants name
         */
        bstorename.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, ChangeStorenameActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to change merchants password
         */
        bstorepassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, ChangeStorepasswordActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to change merchants main business
         */
        bmainbusiness.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, ChangeStoreBusinessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to change merchants avatar
         */
        bstoreavatar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, ChangeStorenameActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to chagne merchants address
         */
        bstoreaddress.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, ChangeStoreAddressActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, StoreCommunityActivity.class);
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
                Intent intent = new Intent(StoreCenterActivity.this, StoreHomeActivity.class);
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
                Intent intent = new Intent(StoreCenterActivity.this, StoreCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to communication list
         */
        commu_icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCenterActivity.this, StoreFriendsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    /**
     * turn to log in site
     * @param view
     */
    public void skip_to_Main(View view){
        Intent intent=new Intent();
        intent.setClass(StoreCenterActivity.this,MainActivity.class);
        startActivity(intent);
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
