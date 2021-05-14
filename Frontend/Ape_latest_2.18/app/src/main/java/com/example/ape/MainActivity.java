package com.example.ape;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ape.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    TextView tusername,tpassword;
    Button leftbutton,rightbutton;
    AlertDialog.Builder builder;
    String server_url = "http://coms-309-hv-5.cs.iastate.edu/registeri.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        tusername = findViewById(R.id.Input_Username);
        tpassword = findViewById(R.id.Input_Password);
        leftbutton = findViewById(R.id.bRegister);
        rightbutton = findViewById(R.id.bLogIn);
        builder = new AlertDialog.Builder(MainActivity.this);

        leftbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                tusername.setText("");
                tpassword.setText("");
                startActivity(new Intent("com.litreily.RegisterActivity"));
            }
        });

        rightbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String username = tusername.getText().toString();
                final String password = tpassword.getText().toString();
                if(!username.isEmpty() && !password.isEmpty()){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                if(code.equals("register_failed")){
                                    builder.setTitle("Login Success!");
                                    builder.setMessage("Welcome!");
                                    displayAlert(code);
                                }else{
                                    builder.setTitle("Login Error");
                                    builder.setMessage("Username or Password is wrong.");
                                    displayAlert(code);
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError{
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username",username);
                            params.put("password",password);
                            return params;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(stringRequest);
                }
                else{
                    builder.setTitle("Information Wrong!");
                    builder.setMessage("Please enter valid information!");
                    displayAlert("log_failed");
                }
            }
        });
    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("register_failed")){
                    tusername.setText("");
                    tpassword.setText("");
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    tusername.setText("");
                    tpassword.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
