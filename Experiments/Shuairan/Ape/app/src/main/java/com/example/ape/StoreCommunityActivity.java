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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuairan Chen
 * show all type posts
 */
public class StoreCommunityActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    TextView restaurants,beverages,entertainments,departments,apartments;
    TextView post1,post2,post3,post4,post5,post6,post7,post8,post9,post10,post11,post12,post13,post14;
    String pi1,pi2,pi3,pi4,pi5,pi6,pi7,pi8,pi9,pi10,pi11,pi12,pi13,pi14;
    Button bcommunity,bhome,bperson;

    String server_url = "http://192.168.56.1:8888/posts/listPostsVOByMerchantsType";

    //String server_url = "http://coms-309-hv-5.cs.iastate.edu:8888/merchants/getMerchantsByUsersId";
    //String server_url_cp2 = "http://coms-309-hv-5.cs.iastate.edu:8888/comments/listCommentsVOByMerchantsId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_community_site);
        Log.d(TAG, "onCreate: ");

        restaurants = findViewById(R.id.resposts);
        beverages = findViewById(R.id.beverposts);
        entertainments = findViewById(R.id.entertainposts);
        departments = findViewById(R.id.depstoreposts);
        apartments = findViewById(R.id.apartposts);

        post1 = findViewById(R.id.post1);
        post2 = findViewById(R.id.post2);
        post3 = findViewById(R.id.post3);
        post4 = findViewById(R.id.post4);
        post5 = findViewById(R.id.post5);
        post6 = findViewById(R.id.post6);
        post7 = findViewById(R.id.post7);
        post8 = findViewById(R.id.post8);
        post9 = findViewById(R.id.post9);
        post10 = findViewById(R.id.post10);
        post11 = findViewById(R.id.post11);
        post12 = findViewById(R.id.post12);
        post13 = findViewById(R.id.post13);
        post14 = findViewById(R.id.post14);

        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        setposting("restaurants", server_url);

        /**
         * show all restaurants' posts
         */
        restaurants.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                clearposts();
                setposting("restaurants", server_url);
            }
        });

        /**
         * show all beverages' posts
         */
        beverages.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                clearposts();
                setposting("beverages", server_url);
            }
        });

        /**
         * show all entertainments' posts
         */
        entertainments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                clearposts();
                setposting("entertainments", server_url);
            }
        });

        /**
         * show all departments' posts
         */
        departments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                clearposts();
                setposting("departments", server_url);
            }
        });

        /**
         * show all apartments' posts
         */
        apartments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                clearposts();
                setposting("apartments", server_url);
            }
        });

        /**
         * turn to post site
         */
        post1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post1.getText());
                intent.putExtra("pid", pi1);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post2.getText());
                intent.putExtra("pid", pi2);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post3.getText());
                intent.putExtra("pid", pi3);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post4.getText());
                intent.putExtra("pid", pi4);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post5.getText());
                intent.putExtra("pid", pi5);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post6.getText());
                intent.putExtra("pid", pi6);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post7.getText());
                intent.putExtra("pid", pi7);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post8.getText());
                intent.putExtra("pid", pi8);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post9.getText());
                intent.putExtra("pid", pi9);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post10.getText());
                intent.putExtra("pid", pi10);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post11.getText());
                intent.putExtra("pid", pi11);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post12.getText());
                intent.putExtra("pid", pi12);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post13.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post13.getText());
                intent.putExtra("pid", pi13);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        post14.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, PostsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("posttitle",post14.getText());
                intent.putExtra("pid", pi14);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreCommunityActivity.this, StoreCommunityActivity.class);
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
                Intent intent = new Intent(StoreCommunityActivity.this, StoreHomeActivity.class);
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
                Intent intent = new Intent(StoreCommunityActivity.this, StoreCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    /**
     * get posts
     * @param storetype
     * @param url1
     */
    public void setposting(final String storetype, String url1){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray arr = jsonObject.getJSONArray("data");
                    JSONObject jo = null;
                    List datalist = new ArrayList();
                    for (int i = 0; i < arr.length(); i++) {
                        jo = arr.getJSONObject(i);
                        datalist.add(arr.get(i).toString());
                    }
                    for (int i = 0; i < datalist.size(); i++) {
                        JSONObject jdata = new JSONObject((String) datalist.get(i));
                        final int index = i;

                        String uname = jdata.optString("title");
                        String pid = jdata.optString("id");

                        if (index == 0) {
                            post1.setText(uname);
                            pi1 = pid;
                        } else if (index == 1) {
                            post2.setText(uname);
                            pi2 = pid;
                        } else if (index == 2) {
                            post3.setText(uname);
                            pi3 = pid;
                        } else if (index == 3) {
                            post4.setText(uname);
                            pi4 = pid;
                        } else if (index == 4) {
                            post5.setText(uname);
                            pi5 = pid;
                        } else if (index == 5) {
                            post6.setText(uname);
                            pi6 = pid;
                        } else if (index == 6) {
                            post7.setText(uname);
                            pi7 = pid;
                        } else if (index == 7) {
                            post8.setText(uname);
                            pi8 = pid;
                        } else if (index == 8) {
                            post9.setText(uname);
                            pi9 = pid;
                        } else if (index == 9) {
                            post10.setText(uname);
                            pi10 = pid;
                        } else if (index == 10) {
                            post11.setText(uname);
                            pi11 = pid;
                        } else if (index == 11) {
                            post12.setText(uname);
                            pi12 = pid;
                        } else if (index == 12) {
                            post13.setText(uname);
                            pi13 = pid;
                        } else if (index == 13) {
                            post14.setText(uname);
                            pi14 = pid;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StoreCommunityActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("merchantsType",storetype);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    /**
     * initial posts
     */
    public void clearposts(){
        post1.setText("");
        post2.setText("");
        post3.setText("");
        post4.setText("");
        post5.setText("");
        post6.setText("");
        post7.setText("");
        post8.setText("");
        post9.setText("");
        post10.setText("");
        post11.setText("");
        post12.setText("");
        post13.setText("");
        post14.setText("");
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
