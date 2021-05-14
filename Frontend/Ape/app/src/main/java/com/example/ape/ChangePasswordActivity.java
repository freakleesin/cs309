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
 * Normal users change password
 */
public class ChangePasswordActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    Button bsubmit,bcommunity,bhome,bperson;
    TextView cusername,cpassword,npassword,conpassword;
    //AlertDialog.Builder builder;
    String server_url_cp = "http://192.168.56.1:8888/users/updatePassword";
    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/users/updatePassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_site);
        Log.d(TAG, "onCreate: ");

        cusername = findViewById(R.id.rpusername);
        cpassword = findViewById(R.id.nowpassword);
        npassword = findViewById(R.id.newpassword);
        conpassword = findViewById(R.id.conpassword);

        bsubmit = findViewById(R.id.Password_Submit);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * send json request, and submit username, password, and new password
         */
        bsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String nowusername = cusername.getText().toString();
                final String nowpassword = cpassword.getText().toString();
                final String newpassword = npassword.getText().toString();
                final String conpass = conpassword.getText().toString();
                if(!nowusername.isEmpty() && !nowpassword.isEmpty() && !newpassword.isEmpty() && !conpass.isEmpty()){
                    if(newpassword.equals(conpass)) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_cp, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String code = jsonObject.optString("status");
                                    if (code.equals("fail")) {
                                        Toast.makeText(ChangePasswordActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                                        clearinformation();
                                    } else{
                                        Toast.makeText(ChangePasswordActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                        clearinformation();
                                        Intent intent = new Intent(ChangePasswordActivity.this, PersonalCenterActivity.class);
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
                                Toast.makeText(ChangePasswordActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("username", nowusername);
                                params.put("password", nowpassword);
                                params.put("newpassword", newpassword);
                                return params;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(stringRequest);
                    }else{
                        Toast.makeText(ChangePasswordActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                        clearinformation();
                    }
                }
                else{
                    Toast.makeText(ChangePasswordActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    clearinformation();
                }
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChangePasswordActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(ChangePasswordActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(ChangePasswordActivity.this, PersonalCenterActivity.class);
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
        cusername.setText("");
        cpassword.setText("");
        npassword.setText("");
        conpassword.setText("");
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