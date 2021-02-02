package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Resultdisplay extends AppCompatActivity {
Button predictagain;
Button logout;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultdisplay);
        logout = findViewById(R.id.logout);

        Intent intent= getIntent();
        String result= intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView resultdisplay = findViewById(R.id.result);
        mAuth = FirebaseAuth.getInstance();
        resultdisplay.setText(result);
        predictagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultdisplay.this, MainActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mAuth.getInstance().signOut();
              finish();
              Intent i = new Intent(Resultdisplay.this, login.class);
              startActivity(i);
            }
        });

    }
}