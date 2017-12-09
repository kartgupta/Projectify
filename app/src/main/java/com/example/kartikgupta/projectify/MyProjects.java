package com.example.kartikgupta.projectify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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

import java.util.ArrayList;

public class MyProjects extends Activity {

    private ListView listViewApplied;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> t1=new ArrayList<>();
    ArrayList<String> d1=new ArrayList<>();

    class dataListAdapter extends BaseAdapter {
        ArrayList<String> Title=new ArrayList<>();
        ArrayList<String> Detail=new ArrayList<>();

        dataListAdapter() {
            Title = null;
            Detail = null;
        }

        public dataListAdapter(ArrayList<String> text, ArrayList<String> text1) {
            Title = text;
            Detail = text1;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return Title.size();
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.projectlayout, parent, false);
            TextView textViewProjectName = (TextView) row.findViewById(R.id.textViewProjectName);
            TextView textViewProjectDescription = (TextView) row.findViewById(R.id.textViewProjectDescription);
            textViewProjectName.setText(Title.get(position));
            textViewProjectDescription.setText(Detail.get(position));
            return (row);
        }
    }


    //add menu bar
    private Button buttonMyProject;
    private Button buttonProfile;
    private Button buttonProject;
    //private ImageButton imageButton2;
    //menu bar end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);

        listViewApplied = findViewById(R.id.listViewApplied);

        //menu bar
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonMyProject = (Button) findViewById(R.id.buttonMyProject);
        buttonProject = (Button) findViewById(R.id.buttonProject);
 //       imageButton2 = (ImageButton) findViewById(R.id.btnCreateProject);

//        imageButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(MyProjects.this, NewProject.class);
//                startActivity(intent);
//
//            }
//        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects.this, UpdateProfile.class);
                startActivity(intent);

            }
        });

        buttonMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects.this, MyProjects.class);
                startActivity(intent);

            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyProjects.this, ProjectList.class);
                startActivity(intent);

            }
        });

        //menu bar end

        adapter = new ArrayAdapter<String>(this, R.layout.projectlayout, R.id.textViewProjectName, list);
        //listViewApplied.setAdapter(adapter);
        final MyProjects.dataListAdapter adapterNew = new MyProjects.dataListAdapter(t1,d1);
        listViewApplied.setAdapter(adapterNew);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Projects");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String useremail = user.getEmail();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String foundApplied = snapshot.child("projectApplicants").getValue().toString();
                    String projectname = snapshot.child("projectName").getValue().toString();
                    String projectDescription = snapshot.child("projectDescription").getValue().toString();
                    if(foundApplied.equals(useremail)) {
                        t1.add(0, projectname);
                        d1.add(0, projectDescription);
                        adapterNew.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        listViewApplied.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ProjectList.this, "text"+, Toast.LENGTH_SHORT).show();
                int position = i;
                //String value = listViewApplied.getItemAtPosition(position).toString();
                String value = ((TextView) view.findViewById(R.id.textViewProjectName)).getText().toString();
                //Toast.makeText(MyProjects.this, "text "+i+" "+value, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MyProjects.this, Project1.class);
                intent.putExtra("value", value);
                startActivity(intent);

            }
        });


    }

    //go to new project when click on the icon at top right corner
    public void CreatProject(View view){
        Intent intent = new Intent();
        intent.setClass(MyProjects.this, NewProject.class);
        startActivity(intent);
    }
}
