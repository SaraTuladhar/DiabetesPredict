package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.diabetesprediction.EXTRA_TEXT";

    EditText pregnancyInput;
    EditText glucoseInput;
    EditText bpInput;
    EditText skinThickInput;
    EditText insulinInput;
    EditText bmiInput;
    EditText dpfInput;
    EditText ageInput;

    Button predictButton;
    Button toBmiCalc;
    TextView resultText;
    Interpreter tfLite;
RadioButton radioButton,radioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregnancyInput = (EditText) findViewById(R.id.pregnancyInput);
        glucoseInput = (EditText) findViewById(R.id.glucoseInput);
        bpInput = (EditText) findViewById(R.id.bpInput);
        skinThickInput = (EditText) findViewById(R.id.skinThickInput);
        insulinInput = (EditText) findViewById(R.id.insulinInput);
        bmiInput = (EditText) findViewById(R.id.bmiInput);
        dpfInput = (EditText) findViewById(R.id.dpfInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        predictButton = (Button) findViewById(R.id.predictButton);
        toBmiCalc = (Button) findViewById(R.id.tobmicalc);
        resultText = (TextView) findViewById(R.id.result);
        radioButton=findViewById(R.id.radioButton);
        radioButton2=findViewById(R.id.radioButton2);


        /* BMI calculator entry */
        toBmiCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, BMIcalculator.class);
                startActivity(intent);
            }
        });

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //error situation in case of empty
                if (ageInput.length()==0||pregnancyInput.length()==0||glucoseInput.length()==0||bpInput.length()==0||skinThickInput.length()==0||bmiInput.length()==0||dpfInput.length()==0||insulinInput.length()==0)
                {
                    ageInput.setError("Empty");
                    pregnancyInput.setError("Empty");
                    glucoseInput.setError("Empty");
                    bpInput.setError("Empty");
                    skinThickInput.setError("Empty");
                    bmiInput.setError("Empty");
                    dpfInput.setError("Empty");
                    insulinInput.setError("Empty");
                }
                else if (ageInput.length()==0)
                {
                    ageInput.setError("Empty");
                }
                else if (pregnancyInput.length()==0)
                {
                    pregnancyInput.setError("Empty");
                }
               else if (glucoseInput.length()==0)
                {
                    glucoseInput.setError("Empty");
                }
                else if (bpInput.length()==0)
                {
                    bpInput.setError("Empty");
                }
                else if (skinThickInput.length()==0)
                {
                    skinThickInput.setError("Empty");
                }
                else if (bmiInput.length()==0)
                {
                    bmiInput.setError("Empty");
                }
                else if (dpfInput.length()==0) {
                    dpfInput.setError("Empty");
                }
                else if (insulinInput.length()==0) {
                    insulinInput.setError("Empty");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Recode added!", Toast.LENGTH_LONG);
                }

               /* //radiobutton
                if(radioButton.isChecked())
                {
                    Toast.makeText(getApplicationContext(),""+radioButton.getText().toString(),Toast.LENGTH_SHORT);

                }
                else if(radioButton2.isChecked())
                {
                    Toast.makeText(getApplicationContext(),""+radioButton2.getText().toString(),Toast.LENGTH_SHORT);
                }
*/

                //directing to Homepage which will in turn direct it to thi main page where all the data will be input
                Intent intent= new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);

                float prediction = doInference();

                // Prediction failure. Show error message
                if (prediction == -1.0f) {
                    intent= new Intent(MainActivity.this, Resultdisplay.class);
                    intent.putExtra(EXTRA_TEXT, "Error: Please check your inputs!");
                    startActivity(intent);
                    return;
                };

                // Prediction success. Show appropriate message
                String result = prediction == 0.0f ? "No Diabetes. You seem to be fine.\n\n" +
                        "Since your blood glucose level, blood Pressure, insulin level, and  bmi are below the recommended threshold, Your chance of having diabetes in the near future reduces by 25%\n ": "Oh no! You have diabetes.\n\n" +
                        "Since your blood glucose level, blood Pressure, insulin level, or  bmi are above the recommended threshold, You have a 50% more chance of having diabetes in the near future \n ";


                /*Intent intent1 = new Intent(getBaseContext(), MainActivity.class);

                startActivity(intent);*/

                //The idea is to go to the resultdisplay activity for viewing the result
                intent= new Intent(MainActivity.this, Resultdisplay.class);
                intent.putExtra(EXTRA_TEXT, result);
                startActivity(intent);

            }
        });

        try{
            tfLite = new Interpreter((loadModelFile()));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public float doInference() {
        try {

            // Get values from the inputs when predict button is pressed
            float pregnancy = Float.parseFloat(pregnancyInput.getText().toString());
            float glucose = Float.parseFloat(glucoseInput.getText().toString());
            float bp = Float.parseFloat(bpInput.getText().toString());
            float skinThickness = Float.parseFloat(skinThickInput.getText().toString());
            float insulin = Float.parseFloat(insulinInput.getText().toString());
            float bmi = Float.parseFloat(bmiInput.getText().toString());
            float dpf = Float.parseFloat(dpfInput.getText().toString());
            float age = Float.parseFloat(ageInput.getText().toString());

            // Model takes input as array of arrays -> [[23, 34, ...]]
            float[][] inputs = {{pregnancy, glucose, bp, bmi, insulin, skinThickness, dpf, age}};

            // Output is array of array -> [[0.1415]]
            float[][] output = new float[1][1];

            tfLite.run(inputs, output);

            // If output is >0.5f, then the person has diabetes
            if (output[0][0] > 0.5f) return 1.0f;
            return 0.0f;



        } catch (Exception e) {
            System.out.println(e.toString());
            return -1.0f;
        }
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd( "working_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset, declaredLength);
    }
}