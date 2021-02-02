package com.example.diabetesprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView ProfilesignupName,  ProfilesignupEmail, ProfileAge;
    Button Backtopredict;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseUser fuser;
    User user;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
                // Hooking
        ProfilesignupName = (TextView) findViewById(R.id.proname);
        ProfilesignupEmail = (TextView) findViewById(R.id.promail);
        ProfileAge = (TextView) findViewById(R.id.proage);
        Backtopredict = (Button) findViewById(R.id.backtopredict);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
     /*  reference.getRef(mAuth.getUid());*/

       /*reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               ProActivity userprofile = dataSnapshot.getValue(ProActivity.class);
               ProfilesignupName.setText();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });*/
        DatabaseReference rootRef = db.getReference();
       /* DownloadManager.Query query = rootRef.child("User").orderByChild("signupEmail").equalsTo(String.valueOf(fuser.getEmail()));
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Log.d(TAG, key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);*/

    }
}