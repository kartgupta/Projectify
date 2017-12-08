package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewProject extends Activity implements View.OnClickListener{


    private EditText editTextProjectName, editTextRole, editTextProjectDesc;
    private Button buttonJunior;
    private Button buttonManger;
    private Button buttonSenior;
    private Button buttonPost;
    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    private ImageButton imageButton2;
    //menu bar end
    public String category = new String();


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
        buttonJunior = findViewById(R.id.buttonJunior);
        buttonManger = findViewById(R.id.buttonManager);
        buttonSenior = findViewById(R.id.buttonSenior);

        buttonPost.setOnClickListener(this);
        buttonJunior.setOnClickListener(this);
        buttonManger.setOnClickListener(this);
        buttonSenior.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference projectRef = database.getReference("Projects");


        if (view == buttonJunior){

            category= "Junior";
        }
        else if (view == buttonSenior){
            category = "Senior";
        }
        else if (view == buttonManger){
            category = "Manager";
        }

        else if (view == buttonPost){

            String projectName = editTextProjectName.getText().toString();
            String projectRole = editTextRole.getText().toString();
            String projectDescription = editTextProjectDesc.getText().toString();
            String applicants = "N/A";

            Project myProject = new Project(projectName, category, projectRole, projectDescription, applicants, true);
            projectRef.push().setValue(myProject);

            Intent intent = new Intent(NewProject.this, ProjectList.class);
            startActivity(intent);
        }


        //myRef.push().setValue(editTextProjectName.getText().toString());
        //editTextProjectName.setText("");


    }
}
