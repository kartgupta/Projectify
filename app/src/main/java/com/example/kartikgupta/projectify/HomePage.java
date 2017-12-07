package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends Activity implements View.OnClickListener{

    private Button buttonSave;
    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    //menu bar end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, ProjectList.class);
                startActivity(intent);

            }
        });

        //menu bar end

        //buttonSave = (Button) findViewById(R.id.buttonSave);
        //buttonProjects = (Button) findViewById(R.id.buttonProjects);
        //buttonMyProjects = (Button) findViewById(R.id.buttonMyProjects);
        //buttonProfile = (Button) findViewById(R.id.buttonProfile);

        //buttonProfile.setOnClickListener(this);
        //buttonMyProjects.setOnClickListener(this);
        //buttonSave.setOnClickListener(this);
        //buttonProjects.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
