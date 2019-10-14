package com.example.aneshkagoyal.stela;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main1Activity extends AppCompatActivity {

    Button paid,trial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        paid = (Button) findViewById(R.id.paid);
        trial = (Button) findViewById(R.id.trial);
        paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main1Activity.this, MainActivity.class));
            }
        });
        trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main1Activity.this, MainActivity.class));
            }
        });
    }
}
