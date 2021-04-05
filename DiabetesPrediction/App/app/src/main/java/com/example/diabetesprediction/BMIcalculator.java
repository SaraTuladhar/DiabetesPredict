package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMIcalculator extends AppCompatActivity {
    EditText height, weight;
    TextView bmiresultdisplay;
    String BMIresult, calculation;
    Button backtoinput, calculatebmi;
    float a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_icalculator);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        calculatebmi=(Button)findViewById(R.id.calculatebmi);
        bmiresultdisplay = (TextView) findViewById(R.id.bmiresultdisplay);
        backtoinput = (Button) findViewById(R.id.backtoinput);


        backtoinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMIcalculator.this, MainActivity.class);
                startActivity(intent);
            }
        });

        calculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (height.length() == 0|| weight.length() == 0) {
                    height.setError("Empty");
                    weight.setError("Empty");
                    a++;
                }
                else if (height.length() == 0) {
                    height.setError("Empty");
                    a++;
                }
                else if (weight.length() == 0) {
                    weight.setError("Empty");
                    a++;
                }
                else {

                    Toast.makeText(BMIcalculator.this, "Calculated!", Toast.LENGTH_LONG);
                    a = 0;
                }
                Log.d("value of a", String.valueOf(a));
                calculateBMI();
            }
            /*float data=  calculateBMI();*/

    public void calculateBMI() {
        String s2 = weight.getText().toString();
        String s1 = height.getText().toString();

        float weightval = Float.parseFloat(s1);
        float heightval = Float.parseFloat(s2)/100;
        float bmi = weightval / (heightval * heightval);

        if (bmi < 16) {
            BMIresult = "Severly Underweight";
        } else if (bmi < 18.5) {
            BMIresult = "Underweight:" +
                    "Your weight is lower than a normal body weight. Try to make an appetite!" ;
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal:" +
                    "Your weight is normal. Good Work, Keep it up!";
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "Overweight" +
                    "Your weight is above than a normal body weight. Try to exercise more!!";
        } else {
            BMIresult = "Obese";
        }
        calculation = "Result:\n\n" + bmi + "\n" + BMIresult;
        bmiresultdisplay.setText(calculation);
    }
        });
    }
}