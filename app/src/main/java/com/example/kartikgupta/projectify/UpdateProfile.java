package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateProfile extends Activity implements View.OnClickListener{

    private EditText editTextUpSkill, editTextUpInterest, editTextUpExperience, editTextUpDesignation, editTextUpMoreInfo;
    private Button buttonUpdateProfile;
    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    //menu bar end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UpdateProfile.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UpdateProfile.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UpdateProfile.this, ProjectList.class);
                startActivity(intent);

            }
        });

        //menu bar end


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
