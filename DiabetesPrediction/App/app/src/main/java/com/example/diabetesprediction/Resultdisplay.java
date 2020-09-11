package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultdisplay extends AppCompatActivity {
Button predictagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultdisplay);
        predictagain = findViewById(R.id.predictagain);

        Intent intent= getIntent();
        String result= intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView resultdisplay= findViewById(R.id.result);

        resultdisplay.setText(result);
        predictagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultdisplay.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}