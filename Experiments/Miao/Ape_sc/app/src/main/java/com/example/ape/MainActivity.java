package com.example.ape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String TAG = "MyTAG";
    TextView textview;
    Button leftbutton,rightbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        textview = findViewById(R.id.WelcomeMessage);
        leftbutton = findViewById(R.id.bRegister);
        rightbutton = findViewById(R.id.bLogIn);

        leftbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent("com.litreily.RegisterActivity"));
            }
        });

        rightbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                textview.setText(R.string.Login_Text);
                Timer timer = new Timer();
                TimerTask tast = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(new Intent("com.litreily.HomeActivity"));
                    }
                };
                timer.schedule(tast,1000);
                //startActivity(new Intent("com.litreily.HomeActivity"));
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
