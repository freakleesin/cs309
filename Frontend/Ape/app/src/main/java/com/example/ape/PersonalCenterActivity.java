package com.example.ape;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
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
 * personal center menu
 */
public class PersonalCenterActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    TextView tusername;
    ImageView friendslist;
    Button bcusername,bcpassword,bcavatar,bposting,blogout,bcommunity,bhome,bperson;
    String server_url = "http://192.168.56.1:8888/users/get";
    //String server_url = "http://coms-309-hv-5.cs.iastate.edu:8888/users/get";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);
        Log.d(TAG, "onCreate: ");

        tusername = findViewById(R.id.Username);

        bcusername = findViewById(R.id.ChangeU);
        bcpassword = findViewById(R.id.ChangeP);
        bcavatar = findViewById(R.id.ChangeA);
        blogout = findViewById(R.id.LogOut);
        bposting = findViewById(R.id.Postings);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);
        friendslist = findViewById(R.id.friends);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * get username
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.optString("data");
                    JSONObject jdata = new JSONObject(data);
                    String uname = jdata.optString("username");
                    tusername.setText("");
                    tusername.append(uname);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PersonalCenterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PersonalCenterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<String,String>();
                params.put("id",id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

        /**
         * log out the account
         */
        blogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        /**
         * turn to change username site
         */
        bcusername.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PersonalCenterActivity.this, ChangeUsernameActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to change password site
         */
        bcpassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PersonalCenterActivity.this, ChangePasswordActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to posting site
         */
        bposting.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PersonalCenterActivity.this, PostingActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to friends list
         */
        friendslist.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PersonalCenterActivity.this, FriendsActivity.class);
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
                Intent intent = new Intent(PersonalCenterActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(PersonalCenterActivity.this, HomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to personal center site
         */
        bperson.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PersonalCenterActivity.this, PersonalCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    /**
     * turn to main site
     * @param view
     */
    public void skip_to_Main(View view){
        Intent intent=new Intent();
        intent.setClass(PersonalCenterActivity.this,MainActivity.class);
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
