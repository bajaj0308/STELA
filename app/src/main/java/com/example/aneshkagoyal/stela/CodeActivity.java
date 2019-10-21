package com.example.aneshkagoyal.stela;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CodeActivity extends AppCompatActivity {

    ImageView imageView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        String num = getIntent().getStringExtra("Expt_number");
        Log.v("Num value in CodeActivity is ", num);
        String expt_num = "Experiment-"+num;
        Log.v("expt_num value in CodeActivity is ", expt_num);
        first = databaseReference.child("Courses").child("IoT").child(expt_num).child("Program");
        imageView = (ImageView) findViewById(R.id.DiagramFragment_ImageView);
        if(num.equals("1")) {
            Drawable res = getResources().getDrawable(R.drawable.expt_one_prog);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("2"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_two_prog);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("3"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_three_prog);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("4"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_four_prog);
            imageView.setImageDrawable(res);
        }
        else if(num.equals("5"))
        {
            Drawable res = getResources().getDrawable(R.drawable.expt_five_prog);
            imageView.setImageDrawable(res);
        }


        //TODO : set image for imageview by fetching data from firebase
        //Creating connection with database
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
              //  Picasso.get().load(link).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
