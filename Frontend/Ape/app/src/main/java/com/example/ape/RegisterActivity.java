package com.example.ape;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ape.app.AppController;
import com.example.ape.utils.Const;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

/**
 * @author Shuairan Chen
 * users could regitser an account
 */
public class RegisterActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    TextView tusername,temail,t1password,t2password, tutype;
    Button bregister;
    AlertDialog.Builder builder;
    String server_url = "http://192.168.56.1:8888/users/register";
    //String server_url = "http://coms-309-hv-5.cs.iastate.edu:8888/users/register";
    //http://192.168.56.1:8888
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_site);
        Log.d(TAG, "onCreate: ");

        tusername = findViewById(R.id.Register_Input_Username);
        temail = findViewById(R.id.editText2);
        t1password = findViewById(R.id.Register_Input_Password);
        t2password = findViewById(R.id.Register_Reinput_Password);
        tutype = findViewById(R.id.ruscc);
        bregister = findViewById(R.id.bRegister);
        builder = new AlertDialog.Builder(RegisterActivity.this);

        /**
         * submit register request
         */
        bregister.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String rusername = tusername.getText().toString();
                final String remail = temail.getText().toString();
                final String rpassword = t1password.getText().toString();
                final String r2password = t2password.getText().toString();
                final String rutype = tutype.getText().toString();
                if (!rusername.isEmpty() && !remail.isEmpty() && !rpassword.isEmpty() && !r2password.isEmpty() && (rutype.equals("Merchant") || rutype.equals("Consumer"))) {
                    if (rpassword.equals(r2password)) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               try{
                                       JSONObject jsonObject = new JSONObject(response);
                                       String code = jsonObject.optString("status");
                                       String message = jsonObject.optString("data");
                                       if(code.equals("success")){
                                           builder.setTitle("Server Response...");
                                           builder.setMessage(code + message);
                                           skip_to_Main();
                                       }else{
                                           builder.setTitle("Server Response...");
                                           builder.setMessage(code + message);
                                       }
                                       displayAlert(code);
                               } catch (JSONException e) {
                                   e.printStackTrace();
                                   Toast.makeText(RegisterActivity.this, "Response Error!", Toast.LENGTH_SHORT).show();
                               }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this, "Request Error!", Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError{
                                Map<String,String> params = new HashMap<String,String>();
                                params.put("username",rusername);
                                params.put("email",remail);
                                params.put("password",rpassword);
                                params.put("usersType", rutype);
                                return params;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(stringRequest);
                    } else {
                        builder.setTitle("Information Wrong!");
                        builder.setMessage("Your passwords are different! Please try again!");
                        displayAlert("fail");
                    }
                } else {
                    builder.setTitle("Incomplete Information!");
                    builder.setMessage("Please complete all information!");
                    displayAlert("register_failed");
                }
            }
        });
    }

    /**
     * show alerts
     * @param code
     */
    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("fail")){
                    t1password.setText("");
                    t2password.setText("");
                }
                else if(code.equals("success")){
                    finish();
                }
                else if(code.equals("register_failed")){
                    tusername.setText("");
                    t1password.setText("");
                    t2password.setText("");
                    tutype.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * turn to log in site
     */
    public void skip_to_Main(){
        Intent intent=new Intent();
        intent.setClass(RegisterActivity.this,MainActivity.class);
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
