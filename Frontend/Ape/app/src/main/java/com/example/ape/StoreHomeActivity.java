package com.example.ape;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class StoreHomeActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    TextView restaurants,beverages,entertainments,departments,apartments;
    Button bcommunity,bhome,bstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storehomesite);
        Log.d(TAG, "onCreate: ");

        restaurants = findViewById(R.id.Restaurants);
        beverages = findViewById(R.id.BeverageShops);
        entertainments = findViewById(R.id.Entertainments);
        departments = findViewById(R.id.DepartmentStores);
        apartments = findViewById(R.id.Apartments);

        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bstore = findViewById(R.id.STORE);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, StoreCommunityActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        bhome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, StoreHomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        bstore.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, StoreCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        beverages.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        entertainments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        departments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        apartments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(StoreHomeActivity.this, RessActivity.class);
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
