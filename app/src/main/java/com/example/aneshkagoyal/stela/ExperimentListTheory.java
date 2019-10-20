package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ExperimentListTheory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_list_theory);
        Button expt1 = (Button) findViewById(R.id.exp1);
        Button expt2 = (Button) findViewById(R.id.exp2);
        Button expt3 = (Button) findViewById(R.id.exp3);
        Button expt4 = (Button) findViewById(R.id.exp4);
        Button expt5 = (Button) findViewById(R.id.exp5);
//        Button expt6 = (Button) findViewById(R.id.exp6);
//        Button expt7 = (Button) findViewById(R.id.exp7);
//        Button expt8 = (Button) findViewById(R.id.exp8);
        expt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
                i.putExtra("Expt Number", "1");
                startActivity(i);
            }
        });
        expt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
                i.putExtra("Expt Number", "2");
                startActivity(i);
            }
        });
        expt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
                //i.putExtra("floor", "ground");
                i.putExtra("Expt Number", "3");
                startActivity(i);
            }
        });
        expt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
                i.putExtra("Expt Number", "4");
                startActivity(i);
            }
        });
        expt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
                i.putExtra("Expt Number", "5");
                startActivity(i);
            }
        });
//        expt6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
//                i.putExtra("Expt Number", "6");
//                startActivity(i);
//            }
//        });
//        expt7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
//                i.putExtra("Expt Number", "7");
//                startActivity(i);
//            }
//        });
//        expt8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ExperimentListTheory.this, CourseContent.class);
//                i.putExtra("Expt Number", "8");
//                startActivity(i);
//            }
//        });
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



//<Button
//            android:id="@+id/exp6"
//                    android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:layout_marginLeft="16dp"
//                    android:layout_marginTop="16dp"
//                    android:layout_marginRight="16dp"
//                    android:background="@drawable/rounded_button"
//                    android:text="Experiment - 6"
//                    android:textColor="#ffffff" />
//
//<Button
//            android:id="@+id/exp7"
//                    android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:layout_marginLeft="16dp"
//                    android:layout_marginTop="16dp"
//                    android:layout_marginRight="16dp"
//                    android:background="@drawable/rounded_button"
//                    android:text="Experiment - 7"
//                    android:textColor="#ffffff" />
//
//<Button
//            android:id="@+id/exp8"
//                    android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:layout_marginLeft="16dp"
//                    android:layout_marginTop="16dp"
//                    android:layout_marginRight="16dp"
//                    android:background="@drawable/rounded_button"
//                    android:text="Experiment - 8"
//                    android:textColor="#ffffff" />
