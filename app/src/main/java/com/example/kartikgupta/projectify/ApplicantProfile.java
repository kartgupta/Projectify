package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class ApplicantProfile extends Activity implements View.OnClickListener{


    private TextView TextViewUpSkill, TextViewUpInterest, TextViewUpExperience, TextViewUpDesignation, TextViewUpMoreInfo, textViewEmail;

    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    private ImageButton imageButton2;
    //menu bar end

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_profile);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);

//        imageButton2 = (ImageButton) findViewById(R.id.btnCreateProject);

//        imageButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(UpdateProfile.this, NewProject.class);
//                startActivity(intent);
//
//            }
//        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ApplicantProfile.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ApplicantProfile.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ApplicantProfile.this, ProjectList.class);
                startActivity(intent);

            }
        });



        //menu bar end

        TextViewUpSkill = findViewById(R.id.TextViewUpSkill);
        TextViewUpInterest = findViewById(R.id.TextViewUpInterest);
        TextViewUpExperience = findViewById(R.id.TextViewUpExperience);
        TextViewUpDesignation = findViewById(R.id.TextViewUpDesignation);
        TextViewUpMoreInfo = findViewById(R.id.TextViewUpMoreInfo);
        textViewEmail = findViewById(R.id.textViewEmail);

        Intent intent = getIntent();
        final String ValueInList = intent.getStringExtra("value");

            final String email = ValueInList;
            //Toast.makeText(ApplicantProfile.this, "text "+ ValueInList, Toast.LENGTH_SHORT).show();

            FirebaseDatabase db = FirebaseDatabase.getInstance();

            final DatabaseReference userRef = db.getReference("UserProfile");
            userRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue() == null) {
//                        TextViewUpSkill.setText("You haven't added profile yet" );
                        //                        Toast.makeText(Main2Activity.this, "card not found", Toast.LENGTH_SHORT).show();

                    }else {
                        userRef.orderByChild("email").equalTo(email).addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                UserProfile finduser = new UserProfile();
                                finduser = dataSnapshot.getValue(UserProfile.class);


                                //                                Toast.makeText(MainActivity.this, "Card limit is: "+findcard.cardLimit, Toast.LENGTH_SHORT).show();
                                textViewEmail.setText(finduser.email);
                                TextViewUpSkill.setText(finduser.skill);
                                TextViewUpInterest.setText(finduser.interest);
                                TextViewUpExperience.setText(finduser.experience);
                                TextViewUpDesignation.setText(finduser.designation);
                                TextViewUpMoreInfo.setText(finduser.moreInfo);


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
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ///test end

    }

    @Override
    public void onClick(View view) {



    }

    //go to new project when click on the icon at top right corner
    public void CreatProject(View view){
        Intent intent = new Intent();
        intent.setClass(ApplicantProfile.this, NewProject.class);
        startActivity(intent);
    }
}
