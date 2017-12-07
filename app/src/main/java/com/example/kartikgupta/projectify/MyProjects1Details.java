package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MyProjects1Details extends Activity {
    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    private ImageButton imageButton2;
    //menu bar end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects1_details);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);
        imageButton2 = (ImageButton) findViewById(R.id.btnCreateProject);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects1Details.this, NewProject.class);
                startActivity(intent);

            }
        });


        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects1Details.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects1Details.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects1Details.this, ProjectList.class);
                startActivity(intent);

            }
        });

        //menu bar end
    }
}
