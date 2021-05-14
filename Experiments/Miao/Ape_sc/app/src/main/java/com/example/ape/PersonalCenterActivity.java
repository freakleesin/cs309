package com.example.ape;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class PersonalCenterActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    Button blogout,bcommunity,bhome,bperson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);
        Log.d(TAG, "onCreate: ");

        blogout = findViewById(R.id.LogOut);
        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

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

    public void skip_to_Main(View view){
        Intent intent=new Intent();
        intent.setClass(PersonalCenterActivity.this,MainActivity.class);
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
