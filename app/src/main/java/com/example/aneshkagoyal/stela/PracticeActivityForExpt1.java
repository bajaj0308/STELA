package com.example.aneshkagoyal.stela;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PracticeActivityForExpt1 extends AppCompatActivity {
DatabaseReference ref;
DatabaseReference ref2;
String device_num ;
    Object num;
 ValueEventListener vl,vl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_for_expt_1);
        num= getIntent().getStringExtra("Expt_number");
        Log.v("Num value in PA is ", num.toString());
        Button runYourExperiment  = (Button) findViewById(R.id.submitInPracticeActivity);
        ref = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref2 = FirebaseDatabase.getInstance().getReference().child("Rpi");
        final Map mp = new HashMap<String,Object>();
        vl=  ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                device_num = dataSnapshot.child("Device").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        runYourExperiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //num is the experiment number
                //TODO : put the experiment number on firebase



              //ref.removeEventListener(vl);


//                vl1 = ref2.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        ref2.child(device_num).setValue(num);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
                //ref2.removeEventListener(vl1);

                mp.put(device_num,num);
                ref2.updateChildren(mp).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(PracticeActivityForExpt1.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

    }
}
