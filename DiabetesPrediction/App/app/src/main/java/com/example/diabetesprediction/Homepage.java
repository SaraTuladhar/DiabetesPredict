package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Homepage extends AppCompatActivity {
    Button predictnow;
    TextView profilebtn;
    Button glucose_btn, bmi_btn, pressure_btn, preggo_btn, pedigree_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        predictnow= (Button) findViewById(R.id.PredictNow);
        glucose_btn=(Button) findViewById(R.id.Glucose_info);
        bmi_btn= (Button) findViewById(R.id.BMI_info);
        pressure_btn= (Button) findViewById(R.id.Pressure_info);
        preggo_btn= (Button) findViewById(R.id.Preggo_info);
        pedigree_btn= (Button) findViewById(R.id.Pedigree_info);
        profilebtn=(TextView)findViewById(R.id.Profilebtn);

        predictnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        glucose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, Infodisplay.class);
                startActivity(intent);
            }
        });
        bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, Infodisplay.class);
                startActivity(intent);
            }
        });
        pressure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, Infodisplay.class);
                startActivity(intent);
            }
        });
        preggo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, Infodisplay.class);
                startActivity(intent);
            }
        });
        pedigree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, Infodisplay.class);
                startActivity(intent);
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Homepage.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }
}