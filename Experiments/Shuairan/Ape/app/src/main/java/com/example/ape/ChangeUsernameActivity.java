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
 * normal users change username
 */
public class ChangeUsernameActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    TextView cusername,nusername,conusername;
    Button bsubmit,bcommunity,bhome,bperson;
    //AlertDialog.Builder builder;
    String server_url_cu = "http://192.168.56.1:8888/users/updateUsername";
    //String server_url_cu = "http://coms-309-hv-5.cs.iastate.edu:8888/users/updateUsername";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_username_site);
        Log.d(TAG, "onCreate: ");

        cusername = findViewById(R.id.nowusername);
        nusername = findViewById(R.id.newusername);
        conusername = findViewById(R.id.reusername);

        bsubmit = findViewById(R.id.Username_Submit);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * submit new username
         */
        bsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String nowusername = cusername.getText().toString();
                final String newusername = nusername.getText().toString();
                final String conuser = conusername.getText().toString();
                if(!nowusername.isEmpty() && !newusername.isEmpty() && !conuser.isEmpty()){
                    if(newusername.equals(conuser)) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_cu, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String code = jsonObject.optString("status");
                                    if (code.equals("fail")) {
                                        Toast.makeText(ChangeUsernameActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                                        clearinformation();
                                    } else{
                                        Toast.makeText(ChangeUsernameActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                        clearinformation();
                                        Intent intent = new Intent(ChangeUsernameActivity.this, PersonalCenterActivity.class);
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
                                Toast.makeText(ChangeUsernameActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("username", nowusername);
                                params.put("newusername", newusername);
                                return params;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(stringRequest);
                    }else{
                        Toast.makeText(ChangeUsernameActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                        clearinformation();
                    }
                }
                else{
                    Toast.makeText(ChangeUsernameActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    clearinformation();
                }
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ChangeUsernameActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(ChangeUsernameActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(ChangeUsernameActivity.this, PersonalCenterActivity.class);
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
        //cusername.setText("");
        nusername.setText("");
        conusername.setText("");
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