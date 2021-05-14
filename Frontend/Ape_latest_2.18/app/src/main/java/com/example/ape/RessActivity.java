package com.example.ape;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class RessActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    TextView restaurant1;
    Button bcommunity,bhome,bperson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_sumsite);
        Log.d(TAG, "onCreate: ");

        restaurant1 = findViewById(R.id.Restaurants1);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        restaurant1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent("com.litreily.StoreActivity"));
            }
        });

        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent("com.litreily.CommunityActivity"));
            }
        });

        bhome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent("com.litreily.HomeActivity"));
            }
        });

        bperson.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent("com.litreily.PersonalCenterActivity"));
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
