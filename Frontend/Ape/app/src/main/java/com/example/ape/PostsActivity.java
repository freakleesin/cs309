package com.example.ape;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuairan Chen
 * show posts information and users comments
 */
public class PostsActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    String postid = "";
    TextView un1, un2, un3, un4, un5;
    TextView storecoms1,storecoms2,storecoms3,storecoms4,storecoms5;
    ImageView iservice;
    TextView pname;
    Button bcommunity,bhome,bperson,bcom;

    String server_url = "http://192.168.56.1:8888/posts/getPostsVOById";
    String server_url_cp2 = "http://192.168.56.1:8888/commentsStars/listCommentsStarsByPostsId";

    //String server_url = "http://coms-309-hv-5.cs.iastate.edu:8888/merchants/getMerchantsByUsersId";
    //String server_url_cp2 = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/listCommentsVOByMerchantsId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_site);
        Log.d(TAG, "onCreate: ");

        pname = findViewById(R.id.Posting_Title);

        un1 = findViewById(R.id.up1);
        un2 = findViewById(R.id.up2);
        un3 = findViewById(R.id.up3);
        un4 = findViewById(R.id.up4);
        un5 = findViewById(R.id.up5);

        storecoms1 = findViewById(R.id.upc1);
        storecoms2 = findViewById(R.id.upc2);
        storecoms3 = findViewById(R.id.upc3);
        storecoms4 = findViewById(R.id.upc4);
        storecoms5 = findViewById(R.id.upc5);

        bcom = findViewById(R.id.Go_Posts_Comment);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String pid = intent.getStringExtra("pid");
        final String postname = intent.getStringExtra("posttitle");
        final String username = intent.getStringExtra("username");
        pname.setText(postname);

        /**
         * get post content and founders information
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.optString("data");
                    JSONObject jdata = new JSONObject(data);
                    String postId = jdata.optString("id");
                    String merchscomts = jdata.optString("content");
                    String postusername = jdata.optString("username");
                    postid = postId;
                    un1.setText(postusername);
                    storecoms1.setText(merchscomts);
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, server_url_cp2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);

                                JSONArray arr = jsonObject.getJSONArray("data");
                                JSONObject jo = null;
                                List datalist = new ArrayList();
                                for(int i = 0; i < arr.length(); i++){
                                    jo = arr.getJSONObject(i);
                                    datalist.add(arr.get(i).toString());
                                }
                                for(int i=1; i <= datalist.size(); i++){
                                    JSONObject jdata = new JSONObject((String) datalist.get(i - 1));
                                    final String cuser = jdata.optString("username");
                                    final String merchscomts = jdata.optString("content");
                                    if(i == 1){
                                        un2.setText(cuser);
                                        storecoms2.setText(merchscomts);
                                    }else if(i == 2){
                                        un3.setText(cuser);
                                        storecoms3.setText(merchscomts);
                                    }else if(i == 3){
                                        un4.setText(cuser);
                                        storecoms4.setText(merchscomts);
                                    }else if(i == 4){
                                        un5.setText(cuser);
                                        storecoms5.setText(merchscomts);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(PostsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PostsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("postsId",postid);
                            return params;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(stringRequest1);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PostsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("id", pid);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

        /**
         * turn to write posts comments
         */
        bcom.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PostsActivity.this, PostCommentsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("postname",postname);
                intent.putExtra("postId",postid);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PostsActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(PostsActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(PostsActivity.this, PersonalCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

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