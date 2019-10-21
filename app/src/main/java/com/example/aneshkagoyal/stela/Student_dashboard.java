package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student_dashboard extends BaseActivity {
    DatabaseReference reff;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        Button courseb=(Button) findViewById(R.id.course);
        Button signout =(Button) findViewById(R.id.sign_out);
        final TextView st_name = (TextView) findViewById(R.id.name);
        final TextView st_roll = (TextView) findViewById(R.id.roll_no);
        final TextView st_branch = (TextView) findViewById(R.id.branch);
        final TextView st_year = (TextView) findViewById(R.id.year);
        final Button registercourses = findViewById(R.id.register_courses);
        Button regiterDevice = findViewById(R.id.register_device);
       final Spinner dropdown = findViewById(R.id.course_drop);
        auth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference().child("Student").child(auth.getCurrentUser().getUid());
       // Map<String,Object> mp = new HashMap<>();
        final List<String> arr = new ArrayList<>();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            @NonNull
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nm = dataSnapshot.child("name").getValue().toString();
                String br = dataSnapshot.child("branch").getValue().toString();
                String yr = dataSnapshot.child("year").getValue().toString();
                String er = dataSnapshot.child("eroll").getValue().toString();
                if(dataSnapshot.hasChild("Course")){
                    for(DataSnapshot ds: dataSnapshot.child("Course").getChildren()){
                       String mp = ds.getValue().toString();
                       arr.add(mp);


                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Student_dashboard.this, android.R.layout.simple_spinner_dropdown_item, arr);
//set the spinners adapter to the previously created one.
                    dropdown.setAdapter(adapter);
                }
                st_name.setText(nm);
                st_branch.setText(br);
                st_roll.setText(er);
                st_year.setText(yr);


                //Toast.makeText(HomeActivity.this,nm,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        courseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Student_dashboard.this, ExperimentListTheory.class);
                //i.putExtra("floor", "ground");
                startActivity(i);
                finish();
            }
        });
        registercourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student_dashboard.this,Course.class));
            }
        });
        regiterDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student_dashboard.this,RegisterDeviceActivity.class));
                finish();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
