package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed( new Runnable(){
            public void run(){

                Intent t= new Intent(Splash.this, login.class);
        startActivity(t);
        finish();
                }}, 3000);
    }
}