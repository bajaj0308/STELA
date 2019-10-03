package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Student_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        Button courseb=(Button) findViewById(R.id.course);
        courseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Student_dashboard.this, CourseContent.class);
                //i.putExtra("floor", "ground");
                startActivity(i);
            }
        });
    }
}
