package com.navigeto.navigeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import static com.navigeto.navigeto.MainActivity.editSharedpreferences;
import static com.navigeto.navigeto.MainActivity.sharedpreferences;


public class TripInitActivity extends AppCompatActivity {

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editSharedpreferences=sharedpreferences.edit();
        editSharedpreferences.clear();
        editSharedpreferences.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_init);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

}

