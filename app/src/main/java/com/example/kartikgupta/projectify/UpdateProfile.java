package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateProfile extends Activity implements View.OnClickListener{

    private EditText editTextUpSkill, editTextUpInterest, editTextUpExperience, editTextUpDesignation, editTextUpMoreInfo;
    private Button buttonUpdateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        editTextUpSkill = findViewById(R.id.editTextUpSkill);
        editTextUpInterest = findViewById(R.id.editTextUpInterest);
        editTextUpExperience = findViewById(R.id.editTextUpExperience);
        editTextUpDesignation = findViewById(R.id.editTextUpDesignation);
        editTextUpMoreInfo = findViewById(R.id.editTextUpMoreInfo);
        buttonUpdateProfile = findViewById(R.id.buttonUpdateProfile);

        buttonUpdateProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String skill = editTextUpSkill.getText().toString();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference profileRef = db.getReference(skill);

        if(view == buttonUpdateProfile) {
            //user input data
//            String TextSkill = editTextSkill.getText().toString();
            String interest = editTextUpInterest.getText().toString();
            String experience = editTextUpExperience.getText().toString();
            String designation = editTextUpDesignation.getText().toString();
            String moreInfo = editTextUpMoreInfo.getText().toString();
            String email = editTextUpMoreInfo.getText().toString();

            //add to db
            UserProfile newProfile = new UserProfile(email, skill, interest, experience, designation, moreInfo);
            profileRef.push().setValue(newProfile);
        }



    }
}
