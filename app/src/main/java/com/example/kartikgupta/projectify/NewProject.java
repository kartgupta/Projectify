package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class NewProject extends Activity implements View.OnClickListener{


    private EditText editTextProjectName, editTextRole, editTextProjectDesc;
    private Button buttonPost;
    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    private ImageButton imageButton2;
    //menu bar end

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);
        imageButton2 = (ImageButton) findViewById(R.id.btnCreateProject);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(NewProject.this, NewProject.class);
                startActivity(intent);

            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(NewProject.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(NewProject.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(NewProject.this, ProjectList.class);
                startActivity(intent);

            }
        });

        //menu bar end

        editTextProjectName = findViewById(R.id.editTextProjectName);
        editTextRole = findViewById(R.id.editTextRole);
        editTextProjectDesc = findViewById(R.id.editTextProjectDesc);
        buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(this);


    }




    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference projectRef = database.getReference("Projects");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            final String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();

            if (view == buttonPost) {


                String projectRole = editTextRole.getText().toString();
                String projectDescription = editTextProjectDesc.getText().toString();
                String projectOwner = email;
                String applicants = "N/A";
                String projectName = editTextProjectName.getText().toString();

                Project myProject = new Project(projectName, projectRole, projectDescription, applicants, true, projectOwner);
                projectRef.push().setValue(myProject);
                Toast.makeText(NewProject.this,"New project posted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewProject.this, ProjectList.class);
                startActivity(intent);
            }
        }

        //myRef.push().setValue(editTextProjectName.getText().toString());
        //editTextProjectName.setText("");


    }
}
