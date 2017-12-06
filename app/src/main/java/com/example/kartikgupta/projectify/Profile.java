package com.example.kartikgupta.projectify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class Profile extends Activity implements View.OnClickListener{

    private EditText editTextSkill, editTextInterest, editTextExperience, editTextDesignation, editTextMoreInfo, editTextUsername, editTextPassword;
    private Button buttonSaveProfile;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        editTextSkill = findViewById(R.id.editTextSkill);
        editTextInterest = findViewById(R.id.editTextInterest);
        editTextExperience = findViewById(R.id.editTextExperience);
        editTextDesignation = findViewById(R.id.editTextDesignation);
        editTextMoreInfo = findViewById(R.id.editTextMoreInfo);
        buttonSaveProfile = findViewById(R.id.buttonSaveProfile);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSaveProfile.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {
        String skill = editTextSkill.getText().toString();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference profileRef = db.getReference(skill);

        if(view == buttonSaveProfile) {
            //user input data
//            String TextSkill = editTextSkill.getText().toString();
            String interest = editTextInterest.getText().toString();
            String experience = editTextExperience.getText().toString();
            String designation = editTextDesignation.getText().toString();
            String moreInfo = editTextMoreInfo.getText().toString();
            String email = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            //add to db
            UserProfile newProfile = new UserProfile(skill, interest, experience, designation, moreInfo);
            profileRef.push().setValue(newProfile);

            createAccount(email, password);
        }



    }



    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If signin fails, display a message to the user. If signin succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Registeration Failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "Registeration Sucessful",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
