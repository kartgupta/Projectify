package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends Activity implements View.OnClickListener{

    private Button buttonSave, buttonProjects, buttonMyProjects, buttonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
