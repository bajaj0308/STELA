package com.example.aneshkagoyal.stela;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//import com.bumptech.glide.Glide;
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
        if(num.equals("1")) {
            Drawable res = getResources().getDrawable(R.drawable.expt_one_diagram_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("2"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_two_diagram_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("3"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_three_diagram_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("4"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_four_diagram_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("5"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_five_diagram_final);
            imageView.setImageDrawable(res);
        }


        Button backButton = (Button) findViewById(R.id.Diagram_back);
        Button homeButton = (Button) findViewById(R.id.Diagram_home);
        Button nextButton = (Button) findViewById(R.id.Diagram_next);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiagramActivity.this, AboutActivity.class);
                //i.putExtra("floor", "ground");
                i.putExtra("Expt_number", num);
                startActivity(i);
                finish();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiagramActivity.this, ExperimentListTheory.class);
                //i.putExtra("floor", "ground");
                // i.putExtra("Expt_number", num);
                startActivity(i);
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiagramActivity.this,CodeActivity.class);
                //i.putExtra("floor", "ground");
                i.putExtra("Expt_number", num);
                startActivity(i);
                finish();
            }
        });

        //TODO : set image for imageview by fetching data from firebase
        //Creating connection with database
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
               // Picasso.get().load(link).into(imageView);
                //Glide.with(getApplicationContext()).load(link).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}





//    <?xml version="1.0" encoding="utf-8"?>
//
//
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:orientation="vertical"
//        tools:context=".DiagramActivity">
//
//
//<ScrollView
//
//        android:layout_width="match_parent"
//                android:layout_height="wrap_content">
//
//<LinearLayout
//            android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:orientation="vertical">
//
//<ImageView
//                android:id="@+id/DiagramFragment_ImageView"
//                        android:layout_width="fill_parent"
//                        android:layout_height="wrap_content"
//                        android:scaleType="fitXY"
//                        android:src="@drawable/expt_three_about" />
//</LinearLayout>
//</ScrollView>
//<Button
//        android:id="@+id/ToCodeActivity"
//                android:layout_width="wrap_content"
//                android:layout_height="wrap_content"
//                android:layout_marginLeft="300dp"
//                android:layout_marginRight="16dp"
//                android:layout_marginBottom="16dp"
//                android:background="@drawable/rounded_button"
//                android:text="Next"
//                android:textColor="#ffffff" />
//</LinearLayout>