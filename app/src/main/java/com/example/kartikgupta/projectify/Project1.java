package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Project1 extends Activity {

    private TextView textViewProjectName, textViewDescription;




    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    private ImageButton imageButton2;
    //menu bar end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project1);

        textViewProjectName = (TextView) findViewById(R.id.textViewProjectName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);
        imageButton2 = (ImageButton) findViewById(R.id.btnCreateProject);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Project1.this, NewProject.class);
                startActivity(intent);

            }
        });


        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Project1.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Project1.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Project1.this, ProjectList.class);
                startActivity(intent);

            }
        });

        //menu bar end


        //get project name from project list
        Intent intent = getIntent();
        final String ValueInList = intent.getStringExtra("value");
        //Toast.makeText(Project1.this, "text"+position, Toast.LENGTH_SHORT).show();
        //textViewProjectName.setText("test");
        if(ValueInList!=null) {
            textViewProjectName.setText(ValueInList);
        }

        //get other info about project from firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference pjRef = db.getReference("Projects");
        pjRef.orderByChild("projectName").equalTo(ValueInList).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pjRef.orderByChild("projectName").equalTo(ValueInList).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Project project = new Project();
                        project = dataSnapshot.getValue(Project.class);
                        //Toast.makeText(Project1.this, "text"+project.projectDescription, Toast.LENGTH_SHORT).show();
                        textViewDescription.setText(project.projectDescription.toString());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
