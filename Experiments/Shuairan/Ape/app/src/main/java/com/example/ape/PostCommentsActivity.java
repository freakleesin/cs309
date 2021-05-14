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
 * users could write comments in the post
 */
public class PostCommentsActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    TextView sname, ucomment;
    Button bsubmit,bcommunity,bhome,bperson;
    //AlertDialog.Builder builder;
    String server_url_cp = "http://192.168.56.1:8888/commentsStars/newCommentsStars";
    //String server_url_cp = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/newComments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_comments_site);
        Log.d(TAG, "onCreate: ");

        sname = findViewById(R.id.Post_Title);
        ucomment = findViewById(R.id.postusercomment);

        bsubmit = findViewById(R.id.Post_Comment_Submit);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String postId = intent.getStringExtra("postId");
        final String postname = intent.getStringExtra("postname");
        final String username = intent.getStringExtra("username");
        sname.setText(postname);

        /**
         * submit users comments
         */
        bsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String usercomment = ucomment.getText().toString();
                if(!usercomment.isEmpty()) {
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, server_url_cp, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String code = jsonObject.optString("status");
                                if (code.equals("fail")) {
                                    Toast.makeText(PostCommentsActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                                    clearinformation();
                                } else{
                                    Toast.makeText(PostCommentsActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                    clearinformation();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PostCommentsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("usersId", id);
                            params.put("merchantsId", "");
                            params.put("postsId", postId);
                            params.put("stars", "");
                            params.put("content", usercomment);
                            return params;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(stringRequest1);
                }else{
                    Toast.makeText(PostCommentsActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    clearinformation();
                }

                Intent intent = new Intent(PostCommentsActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("pid", postId);
                intent.putExtra("posttitle", postname);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PostCommentsActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(PostCommentsActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(PostCommentsActivity.this, PersonalCenterActivity.class);
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