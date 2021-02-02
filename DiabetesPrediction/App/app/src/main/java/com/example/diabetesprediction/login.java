package com.example.diabetesprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    //Variable declaration
    EditText loginpw, loginemail;
    Button loginbutton;
    TextView gotosignup;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        // Hooking
        loginpw = (EditText) findViewById(R.id.loginpassword);
        loginemail = (EditText) findViewById(R.id.loginemail);
        gotosignup = (TextView) findViewById(R.id.gotosignup);
        loginbutton = (Button) findViewById(R.id.loginbutton);
        mAuth = FirebaseAuth.getInstance();

        myAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser myFirebaseUser = mAuth.getCurrentUser();
                if (myFirebaseUser != null) {
                    Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, Homepage.class);
                    startActivity(i);

                } else {

                    Toast.makeText(login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = loginemail.getText().toString();
                String Password2 = loginpw.getText().toString();
                if (Email.isEmpty() && Password2.isEmpty()) {
                    loginemail.setError("Both Fields are empty");
                    loginemail.requestFocus();
                    loginpw.setError("Both Fields are empty");
                    loginpw.requestFocus();
                }
                else if (Email.isEmpty()) {
                    loginemail.setError("Provide Email");
                    loginemail.requestFocus();
                } else if (Password2.isEmpty()) {
                    loginpw.setError("Provide Password");
                    loginpw.requestFocus();
                } else {
                    mAuth.signInWithEmailAndPassword(Email, Password2).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "Login Error , check whether you are valid email and password", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intToMain = new Intent(login.this, Homepage.class);

                                /* intToMain.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);*/
                                startActivity(intToMain);
                                Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToSignUp = new Intent(login.this, Signup.class);
                startActivity(intToSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(myAuthStateListener);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

}


