package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CourseContent extends BaseActivity {

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
                Intent i = new Intent(CourseContent.this, AboutActivity.class);
                //Bundle extras = getIntent().getStringExtra("Expt Number");
                String num = "";
                //if (extras != null) {
                    num = getIntent().getStringExtra("Expt Number");
                    Log.v("Num value is ", num);
                    //The key argument here must match that used in the other activity
             //   }
                i.putExtra("Expt_number", num);
                startActivity(i);
            }
        });
        String num = getIntent().getStringExtra("Expt Number");;
        if(num.equals("1"))
        {
            practiceb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String nextScreen = "PracticeActivityForExpt";
                    String num = getIntent().getStringExtra("Expt Number");
                    Intent i = new Intent(CourseContent.this, PracticeActivityForExpt1.class);
                    i.putExtra("Expt_number", num);
                    startActivity(i);
                }
            });
        }
        else if(num.equals("2"))
        {
            practiceb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String nextScreen = "PracticeActivityForExpt";
                    String num = getIntent().getStringExtra("Expt Number");
                    Intent i = new Intent(CourseContent.this, PracticeActivityForExpt3.class);
                    i.putExtra("Expt_number", num);
                    startActivity(i);
                }
            });
        }
        else if(num.equals("3"))
        {
            practiceb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String nextScreen = "PracticeActivityForExpt";
                    String num = getIntent().getStringExtra("Expt Number");
                    Intent i = new Intent(CourseContent.this, PracticeActivityForExpt3.class);
                    i.putExtra("Expt_number", num);
                    startActivity(i);
                }
            });
        }

        assessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CourseContent.this, AssessmentActivityForExpt1.class);
                //i.putExtra("floor", "ground");
                startActivity(i);
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