package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {

    ImageView imageView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final String num = getIntent().getStringExtra("Expt_number");
        Log.v("Num value in AboutActivity is ", num);
        String expt_num = "Experiment-"+num;
        Log.v("expt_num value in AboutActivity is ", expt_num);
        first = databaseReference.child("Courses").child("IoT").child(expt_num).child("About");
        imageView = (ImageView) findViewById(R.id.DiagramFragment_ImageView);
        Button nextButton = (Button) findViewById(R.id.ToDiagramActivity);
        if(num.equals("1")) {
            Drawable res = getResources().getDrawable(R.drawable.expt_one_about_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("2"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_two_about_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("3"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_three_about_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("4"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_four_about_final);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("5"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_five_about_final);
            imageView.setImageDrawable(res);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this, DiagramActivity.class);
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
                //Glide.with(getApplicationContext()).load(link).into(imageView);
              //  Picasso.get().load(link).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}


