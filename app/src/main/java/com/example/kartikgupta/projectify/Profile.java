package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends Activity implements View.OnClickListener{

    private EditText editTextSkill, editTextInterest, editTextExperience, editTextDesignation, editTextMoreInfo;
    private Button buttonSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        editTextSkill = findViewById(R.id.editTextSkill);
        editTextInterest = findViewById(R.id.editTextUpInterest);
        editTextExperience = findViewById(R.id.editTextUpExperience);
        editTextDesignation = findViewById(R.id.editTextUpDesignation);
        editTextMoreInfo = findViewById(R.id.editTextUpMoreInfo);
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
            UserProfile newProfile = new UserProfile(skill, interest, experience, designation, moreInfo);
            profileRef.push().setValue(newProfile);
        }



    }
}
