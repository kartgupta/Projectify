package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewProject extends Activity implements View.OnClickListener{


    private EditText editTextProjectName;
    private Button buttonPost;

    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        editTextProjectName = findViewById(R.id.editTextProjectName);
        buttonPost = findViewById(R.id.buttonPost);
    }




    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Project");

        myRef.push().setValue(editTextProjectName.getText().toString());
        editTextProjectName.setText("");


    }
}
