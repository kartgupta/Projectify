package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class NewProject extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
    }


    @Override
    public void onClick(View view) {

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Project");

        //myRef.push().setValue(editTextProjectName.getText().toString());
        //editTextProjectName.setText("");


    }
}
