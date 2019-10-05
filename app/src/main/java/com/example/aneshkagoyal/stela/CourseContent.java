package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);
        Button theoryb = (Button) findViewById(R.id.theory);
        Button practiceb = (Button) findViewById(R.id.practice);
        Button assessment = (Button) findViewById(R.id.assessment);
        theoryb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CourseContent.this, Theoretical.class);
                //i.putExtra("floor", "ground");
                startActivity(i);
            }
        });
    }
}