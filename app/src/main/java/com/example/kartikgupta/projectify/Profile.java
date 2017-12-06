package com.example.kartikgupta.projectify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Activity implements View.OnClickListener{

    private EditText editTextSkill, editTextInterest, editTextExperience, editTextDesignation, editTextMoreInfo;
    private Button buttonSaveProfile;

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

        buttonSaveProfile.setOnClickListener(this);
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

            //add to db
            user newProfile = new user(skill, interest, experience, designation, moreInfo);
            profileRef.push().setValue(newProfile);
        }



    }
}
