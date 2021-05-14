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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuairan Chen
 * normal users rating the store by score and comments
 */
public class RatingActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    TextView sname, ucomment, uscore;
    Button bsubmit,bcommunity,bhome,bperson;
    //AlertDialog.Builder builder;
    String server_url_cp = "http://192.168.56.1:8888/commentsStars/newCommentsStars";
    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/newComments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_site);
        Log.d(TAG, "onCreate: ");

        sname = findViewById(R.id.Rating_Title);
        ucomment = findViewById(R.id.usercomment);
        uscore = findViewById(R.id.inputratingscore);

        bsubmit = findViewById(R.id.Rating_Submit);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String merchantsId = intent.getStringExtra("merchantsId");
        final String storename = intent.getStringExtra("storename");
        final String username = intent.getStringExtra("username");
        sname.setText(storename);

        /**
         * submit score and comments
         */
        bsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String usercomment = ucomment.getText().toString();
                final String userscore = uscore.getText().toString();
                if(!usercomment.isEmpty() && !userscore.isEmpty()) {
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, server_url_cp, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String code = jsonObject.optString("status");
                                if (code.equals("fail")) {
                                    Toast.makeText(RatingActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                                    clearinformation();
                                } else{
                                    Toast.makeText(RatingActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                    clearinformation();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RatingActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("usersId", id);
                            params.put("merchantsId", merchantsId);
                            params.put("postsId", "");
                            params.put("stars", userscore);
                            params.put("content", usercomment);
                            return params;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(stringRequest1);
                }else{
                    Toast.makeText(RatingActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    clearinformation();
                }

                Intent intent = new Intent(RatingActivity.this, PersonalCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("mid",merchantsId);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RatingActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(RatingActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(RatingActivity.this, PersonalCenterActivity.class);
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
        ucomment.setText("");
        uscore.setText("");
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