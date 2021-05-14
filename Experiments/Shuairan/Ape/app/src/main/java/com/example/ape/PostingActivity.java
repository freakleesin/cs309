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
import com.example.ape.MainActivity;
import com.example.ape.R;
import com.example.ape.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuairan Chen
 * normal users writing a new posting title and content
 */
public class PostingActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    TextView postingtitle, store_type, comments;
    Button bsubmit,bcommunity,bhome,bperson;
    String server_url_cp = "http://192.168.56.1:8888/posts/newPosts";
    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/newComments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_site);
        Log.d(TAG, "onCreate: ");

        postingtitle = findViewById(R.id.pttitle);
        store_type = findViewById(R.id.inputst);
        comments = findViewById(R.id.multiAutoCompleteTextView2);

        bsubmit = findViewById(R.id.Rating_Submit);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * submit post title and post content
         */
        bsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Intent intent = getIntent();
                final String id = intent.getStringExtra("id");
                bsubmit.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        final String usercomment = comments.getText().toString();
                        final String storetype = store_type.getText().toString();
                        final String posttitle = postingtitle.getText().toString();
                        if(!usercomment.isEmpty() && !storetype.isEmpty() && !posttitle.isEmpty()) {
                            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, server_url_cp, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String code = jsonObject.optString("status");
                                        if (code.equals("fail")) {
                                            Toast.makeText(PostingActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                                            clearinformation();
                                        } else{
                                            Toast.makeText(PostingActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                            clearinformation();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(PostingActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("usersId", id);
                                    params.put("merchantsType", storetype);
                                    params.put("title", posttitle);
                                    params.put("content", usercomment);
                                    return params;
                                }
                            };
                            AppController.getInstance().addToRequestQueue(stringRequest1);
                        }else{
                            Toast.makeText(PostingActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            clearinformation();
                        }

                        Intent intent = new Intent(PostingActivity.this, PersonalCenterActivity.class);
                        intent.putExtra("id",id);
                        intent.putExtra("username", username);
                        //intent.putExtra("mid",merchantsId);
                        startActivity(intent);
                    }
                });
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PostingActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(PostingActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(PostingActivity.this, PersonalCenterActivity.class);
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
        postingtitle.setText("");
        comments.setText("");
        store_type.setText("");
    }

    public void clearinformation(){
        postingtitle.setText("");
        comments.setText("");
        store_type.setText("");
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