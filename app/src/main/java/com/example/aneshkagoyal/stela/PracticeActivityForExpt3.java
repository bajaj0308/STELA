package com.example.aneshkagoyal.stela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class PracticeActivityForExpt3 extends AppCompatActivity {


    Object num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_for_expt_3);
        num=getIntent().getStringExtra("Expt_number");
        Log.v("Num value in PA is ", num.toString());
        Button runYourExperiment3  = (Button) findViewById(R.id.submitInPracticeActivityForExpt3);

    }
}
