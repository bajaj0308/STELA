package com.example.aneshkagoyal.stela;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DiagramActivity extends AppCompatActivity {

    ImageView imageView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference first;

  //  @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);

        final String num = getIntent().getStringExtra("Expt_number");
        Log.v("Num value in DA is ", num);
        String expt_num = "Experiment-"+num;
        Log.v("expt_num value in is ", expt_num);
        first = databaseReference.child("Courses").child("IoT").child(expt_num).child("Interfacing-Diagram");

        imageView = (ImageView) findViewById(R.id.DiagramFragment_ImageView);
        Button nextButton = (Button) findViewById(R.id.ToCodeActivity);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiagramActivity.this, CodeActivity.class);
                //i.putExtra("floor", "ground");
                i.putExtra("Expt_number", num);
                startActivity(i);
            }
        });

        //TODO : set image for imageview by fetching data from firebase
        //Creating connection with database
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                //Picasso.get().load(link).into(imageView);
                Glide.with(getApplicationContext()).load(link).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
