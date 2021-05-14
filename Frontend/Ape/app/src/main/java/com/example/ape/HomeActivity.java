package com.example.ape;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;

/**
 * @author Shuairan Chen
 * show all store types
 */
public class HomeActivity extends AppCompatActivity{
    String TAG = "MyTAG";
    TextView restaurants,beverages,entertainments,departments,apartments;
    Button bcommunity,bhome,bperson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homesite);
        Log.d(TAG, "onCreate: ");

        restaurants = findViewById(R.id.Restaurants);
        beverages = findViewById(R.id.BeverageShops);
        entertainments = findViewById(R.id.Entertainments);
        departments = findViewById(R.id.DepartmentStores);
        apartments = findViewById(R.id.Apartments);

        bcommunity = findViewById(R.id.COMMUNITY);
        bhome = findViewById(R.id.HOME);
        bperson = findViewById(R.id.PERSONAL);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String username = intent.getStringExtra("username");

        /**
         * turn to community site
         */
        bcommunity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, CommunityActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, PersonalCenterActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        /**
         * turn to restaurants sums site
         */
        restaurants.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                intent.putExtra("storetype", "Restaurants");
                startActivity(intent);
            }
        });

        /**
         * turn to beverages sums site
         */
        beverages.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                intent.putExtra("storetype", "Beverage Shops");
                startActivity(intent);
            }
        });

        /**
         * turn to entertainments sums site
         */
        entertainments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                intent.putExtra("storetype", "Entertainments");
                startActivity(intent);
            }
        });

        /**
         * turn to departments sums site
         */
        departments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                intent.putExtra("storetype", "Department Stores");
                startActivity(intent);
            }
        });

        /**
         * turn to apartments sums site
         */
        apartments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, RessActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("username", username);
                intent.putExtra("storetype", "Apartments");
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
