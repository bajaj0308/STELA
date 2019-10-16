package com.example.aneshkagoyal.stela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PracticeActivityForExpt1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_for_expt_1);
        final String num = getIntent().getStringExtra("Expt_number");
        Log.v("Num value in PracticeActivity is ", num);
        Button runYourExperiment  = (Button) findViewById(R.id.submitInPracticeActivity);
        runYourExperiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //num is the experiment number
                //TODO : put the experiment number on firebase

            }
        });

    }
}
