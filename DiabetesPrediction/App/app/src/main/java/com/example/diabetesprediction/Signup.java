package com.example.diabetesprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword, signupPassword2;
    TextView tologin;
    Button reg;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    User user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        signupName = (EditText) findViewById(R.id.fullname);
        signupUsername = (EditText) findViewById(R.id.SignupUsername);
        signupEmail = (EditText) findViewById(R.id.signupmailid);
        signupPassword = (EditText) findViewById(R.id.signuppw1);
        signupPassword2 = (EditText) findViewById(R.id.signuppw2);
        tologin = (TextView) findViewById(R.id.tologin);
        reg = (Button) findViewById(R.id.signupreg);

        user = new User();
        reference = FirebaseDatabase.getInstance().getReference().child("User");

        /*tologin.setOnClickListener(this);*/
        //reg.setOnClickListener(this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = (signupName.getText().toString());
                String UserName = (signupUsername.getText().toString());
                String Email = (signupEmail.getText().toString());
                String Password = (signupPassword.getText().toString());
                String Password2= (signupPassword2.getText().toString());

                if (Name.isEmpty()) {
                    signupName.setError("Enter Maild Id");
                    signupName.requestFocus();
                } else if (UserName.isEmpty()) {
                    signupUsername.setError("Enter Maild Id");
                    signupUsername.requestFocus();
                } else if (Email.isEmpty()) {
                    signupEmail.setError("Enter Maild Id");
                    signupEmail.requestFocus();
                } else if (Password.isEmpty()) {
                    signupPassword.setError("Enter Mail Id");
                    signupPassword.requestFocus();
                }
                else if (Password2.isEmpty()) {
                    signupPassword2.setError("Enter Mail Id");
                    signupPassword2.requestFocus();
                } else if (signupPassword.length() < 6) {
                    signupPassword.setError("Password should be 6 characters");
                    signupPassword.requestFocus();
                }
                else if (!Password.equals(Password2)) {
                    signupPassword2.setError("Passwords doesn't match!");
                    signupPassword2.requestFocus();
                } else if (Password.equals(Password2)){

                    user.setSignupName(signupName.getText().toString().trim());
                    user.setSignupUsername(signupUsername.getText().toString().trim());
                    user.setSignupEmail(signupEmail.getText().toString().trim());
                    user.setSignupPassword(signupPassword.getText().toString().trim());
                    user.setSignupPassword2(signupPassword2.getText().toString().trim());
                    //String key = reference.child("User").push().getKey();

                    /*String key2 = reference.child("Data").push().getKey();*/
                    /*reference.child(key2).setValue(user);*/
                    mAuth.createUserWithEmailAndPassword(Email, Password2).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Signup.this, "SignUp error occurred", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Signup.this, "Account has been made", Toast.LENGTH_LONG).show();
                                        uid = mAuth.getUid();
                                        Log.d("UID", "onComplete: "+mAuth.getUid());
                                        reference.child(uid).setValue(user);
                                        mAuth.getInstance().signOut();
                                        Toast.makeText(Signup.this, "Account has been made", Toast.LENGTH_LONG).show();
                                        Intent intToLogIn = new Intent(Signup.this, login.class);
                                        startActivity(intToLogIn);

                                    }
                                /*    Log.d("test", "onComplete: " +task.getResult());*/
                                }
                            });

                }
                else {
                    Toast.makeText(Signup.this, "Error Occurred", Toast.LENGTH_LONG).show();
                }

            }
        });

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this, login.class);
                startActivity(i);
            }
        });
    }
}



        /*reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                // Get all values
                String Name=(signupName.getText().toString());
                String UserName=(signupUsername.getText().toString());
                String Email=(signupEmail.getText().toString());
                String Password=(signupPassword.getText().toString());

             UserHelperClass helperclass= new UserHelperClass();
             reference.child(UserName).setValue(helperclass);
            }
        });*/


/*
@Override
public void onClick(View v) {

    if (v == tologin) {

        Intent intent = new Intent(Signup.this, login.class);
        startActivity(intent);


    } else if (v == reg) {
       */
/* Intent intent= new Intent(Signup.this, login.class);
        startActivity(intent);*//*


        }
    }
}
*/

   