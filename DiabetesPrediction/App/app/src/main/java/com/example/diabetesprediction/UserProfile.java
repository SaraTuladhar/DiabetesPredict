package com.example.diabetesprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {
    TextView ProfilesignupName, ProfilesignupEmail, name, email, Previousresult;
    Button Backtopredict, logoutbtn;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference, historyRef;
    FirebaseUser fuser;
    User user;
    String uid;
    private FirebaseAuth.AuthStateListener myAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // Hooking
        ProfilesignupName = (TextView) findViewById(R.id.fullName);
        ProfilesignupEmail = (TextView) findViewById(R.id.email);
        Previousresult = (TextView) findViewById(R.id.previousresult);

        name = (TextView) findViewById(R.id.userFullName);
        email = (TextView) findViewById(R.id.userEmail);

        logoutbtn = (Button) findViewById(R.id.logout);

        Backtopredict = (Button) findViewById(R.id.backtopredict);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        uid = mAuth.getUid();
        reference = db.getReference("User").child(uid);
        historyRef = db.getReference("User").child(uid).child("Previous Value");

        //reference.getRef(mAuth.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserHelperClass userprofile = dataSnapshot.getValue(UserHelperClass.class);
                User user = new User();
                user = (User) dataSnapshot.getValue(User.class);
                ProfilesignupName.setText(user.getSignupName());
                ProfilesignupEmail.setText(user.getSignupEmail());
                Previousresult.setText(user.getResult());
                name.setText(user.getSignupName());
                email.setText(user.getSignupEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT);
            }
        });
        historyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DATASNAPSHOT", "onDataChange: " + dataSnapshot.getValue());
                if (dataSnapshot.getValue() != null)
                    Previousresult.setText(dataSnapshot.getValue().toString() + " %");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(UserProfile.this, login.class);
                startActivity(i);
            }
        });
        Backtopredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Homepage.class);
                startActivity(intent);
            }
        });

    }
}
