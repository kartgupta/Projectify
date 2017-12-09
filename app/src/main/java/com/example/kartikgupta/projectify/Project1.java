package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Project1 extends Activity implements View.OnClickListener{

    private TextView textViewProjectName, textViewDescription, textViewRole, textViewRules;
    private Button buttonApply;

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
        textViewRole = (TextView) findViewById(R.id.textViewRole);
        textViewRules = (TextView) findViewById(R.id.textViewRules);


        buttonApply =(Button) findViewById(R.id.btnApply);
        buttonApply.setOnClickListener(this);

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
                        textViewRole.setText(project.projectRole.toString());
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String useremail = user.getEmail();
                        Boolean Availability = project.projectAvailable;
                        String Applicant = project.projectApplicants;
                        String Owner = project.projectOwner;
                        //Toast.makeText(Project1.this, "useremail: "+useremail+"  Applicant:"+Applicant, Toast.LENGTH_SHORT).show();

                        if(useremail.equals(Owner)) {
                            textViewRules.setText("Applicants");
                            textViewRole.setText(Applicant);
                            buttonApply.setText("View Profile");
                        } else if((!Availability) && (!useremail.equals(Applicant))) {
                            buttonApply.setText("Not Available");
                            buttonApply.setBackgroundColor(Color.GRAY);
                        } else if((!Availability) && (useremail.equals(Applicant))) {
                            buttonApply.setText("Applied");
                            buttonApply.setBackgroundColor(Color.GRAY);
                        }


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

    @Override
    public void onClick(View view) {

        //get project name from project list
        Intent intent = getIntent();
        final String ValueInList = intent.getStringExtra("value");
        if(ValueInList!=null) {
            textViewProjectName.setText(ValueInList);
        }



        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference pjRef = db.getReference("Projects");

        if(view == buttonApply) {
            String buttonText = buttonApply.getText().toString();
            if(buttonText.equals("View Profile")) {
                Intent intentprofile = new Intent(Project1.this, UpdateProfile.class);
                startActivity(intentprofile);
            } else {

                //Toast.makeText(Project1.this, "inside if view==buttonapply", Toast.LENGTH_SHORT).show();
                pjRef.orderByChild("projectName").equalTo(ValueInList).addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        pjRef.orderByChild("projectName").equalTo(ValueInList).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                //get current user email. (user name = null)
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String useremail = user.getEmail();
                                String projectKey = dataSnapshot.getKey();

                                Project project = new Project();
                                project = dataSnapshot.getValue(Project.class);
                                Boolean currentProjectAvailable = project.projectAvailable;

                                if (currentProjectAvailable) {
                                    Toast.makeText(Project1.this, "You successfully applied to this project", Toast.LENGTH_SHORT).show();
                                    pjRef.child(projectKey).child("projectApplicants").setValue(useremail);
                                    //SystemClock.sleep(1000);
                                    pjRef.child(projectKey).child("projectAvailable").setValue(false);
                                    buttonApply.setText("Applied");
                                    buttonApply.setBackgroundColor(Color.GRAY);
                                }


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


    }
}
