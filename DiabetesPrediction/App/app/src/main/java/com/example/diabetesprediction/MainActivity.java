
package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.content.res.AssetFileDescriptor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

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

    Button predictButton , tobmicalc;
    TextView resultText;

    Interpreter tfLite;

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
        tobmicalc= (Button) findViewById(R.id.tobmicalc);
        resultText = (TextView) findViewById(R.id.result);
        /*
        BMI calculator entry*/
        tobmicalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, BMIcalculator.class);
                startActivity(intent);
            }
        });

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //directing to Homepage which will in turn direct it to thi main page where all the data will be input
                Intent intent= new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);

                float prediction = doInference();

                // Prediction failure. Show error message and return
                if (prediction == -1.0) {
                    resultText.setText("Error: Please check your inputs.");
                    return;
                };

                // Prediction success. Show appropriate message
                String result = prediction == 0.0 ? "No Diabetes. You seem to be fine." : "Shit! You have diabetes.";
                

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
            float[][] inputs = {{pregnancy, glucose, bp, skinThickness, insulin, bmi, dpf, age}};

            // Output is array of array -> [[1.0]]
            float[][] output = new float[1][1];

            tfLite.run(inputs, output);
            return output[0][0];
        } catch (Exception e) {
            System.out.println(e.toString());
            return -1.0f;
        }
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd( "converted_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset, declaredLength);
    }
}