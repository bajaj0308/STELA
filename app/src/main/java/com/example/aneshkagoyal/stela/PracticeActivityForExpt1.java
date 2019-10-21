package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
int marks=0;
Button q1_s, q2_s, q3_s, q4_s, q5_s;
    int q1_f=0,q2_f=0,q3_f=0,q4_f=0,q5_f=0;
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
                if(dataSnapshot.hasChild("Device"))
                 device_num = dataSnapshot.child("Device").getValue().toString();
                else{
                    Toast.makeText(PracticeActivityForExpt1.this,"Please go to dashboard to register",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PracticeActivityForExpt1.this,Student_dashboard.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        q1_s = (Button) findViewById(R.id.Q1_submit);
        q2_s = (Button) findViewById(R.id.Q2_submit);
        q3_s = (Button) findViewById(R.id.Q3_submit);
        q4_s = (Button) findViewById(R.id.Q4_submit);
        q5_s = (Button) findViewById(R.id.Q5_submit);
        q1_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ch1_q1= (CheckBox) findViewById(R.id.Q1_checkBox1);
                CheckBox ch2_q1= (CheckBox) findViewById(R.id.Q1_checkBox2);
                CheckBox ch3_q1= (CheckBox) findViewById(R.id.Q1_checkBox3);
                CheckBox ch4_q1= (CheckBox) findViewById(R.id.Q1_checkBox4);
                CheckBox ch5_q1= (CheckBox) findViewById(R.id.Q1_checkBox5);
                if(ch2_q1.isChecked()&&ch3_q1.isChecked()&&!ch1_q1.isChecked()&&!ch4_q1.isChecked()&&!ch5_q1.isChecked()) {
                    q1_f = 1;
                    Toast.makeText(PracticeActivityForExpt1.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q1_f=0;
                    Toast.makeText(PracticeActivityForExpt1.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }

            }
        });
        q2_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank1_q2 = (EditText)findViewById(R.id.Q2_blank1);
                EditText blank2_q2 = (EditText)findViewById(R.id.Q2_blank2);
                EditText blank3_q2 = (EditText)findViewById(R.id.Q2_blank3);
                EditText blank4_q2 = (EditText)findViewById(R.id.Q2_blank4);
                String b1 = blank1_q2.getText().toString();
                String b2 = blank2_q2.getText().toString();
                String b3 = blank3_q2.getText().toString();
                String b4 = blank4_q2.getText().toString();
                if(b1.equals("setmode")&&b2.equals("BCM")&&b3.equals("setup")&&b4.equals("OUT"))
                {
                    q2_f=1;
                    Toast.makeText(PracticeActivityForExpt1.this,"Right Answer!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    q2_f=0;
                    Toast.makeText(PracticeActivityForExpt1.this,"Wrong Answer",Toast.LENGTH_SHORT).show();

                }
            }
        });
        q3_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank_q3 = (EditText) findViewById(R.id.Q3_blank);
                String blank = blank_q3.getText().toString();
                if(blank.equals("GPIO.output(18,GPIO.HIGH)"))
                {
                    q3_f=1;
                    Toast.makeText(PracticeActivityForExpt1.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q3_f=0;
                    Toast.makeText(PracticeActivityForExpt1.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });
        q4_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton r1_q4 = (RadioButton) findViewById(R.id.Q4_radiob1);
                RadioButton r2_q4 = (RadioButton) findViewById(R.id.Q4_radiob2);
                RadioButton r3_q4 = (RadioButton) findViewById(R.id.Q4_radiob3);
                RadioButton r4_q4 = (RadioButton) findViewById(R.id.Q4_radiob4);
                if(r1_q4.isChecked()&&!(r2_q4.isChecked()||r3_q4.isChecked()||r4_q4.isChecked()))
                {
                    q4_f=1;
                    Toast.makeText(PracticeActivityForExpt1.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q4_f=0;
                    Toast.makeText(PracticeActivityForExpt1.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });
        q5_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank1_q5 = (EditText) findViewById(R.id.Q5_blank1);
                EditText blank2_q5 = (EditText) findViewById(R.id.Q5_blank2);
                String blank1 = blank1_q5.getText().toString();
                String blank2 = blank2_q5.getText().toString();
                if(blank1.equals("GPIO.output(18,GPIO.LOW)")&&blank2.equals("time.sleep(1)"))
                {
                    q5_f=1;
                    Toast.makeText(PracticeActivityForExpt1.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q5_f=0;
                    Toast.makeText(PracticeActivityForExpt1.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
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
                if(q1_f==1&&q2_f==1&&q3_f==1&&q4_f==1&&q5_f==1) {

                    mp.put(device_num, num);
                    ref2.updateChildren(mp).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(PracticeActivityForExpt1.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(PracticeActivityForExpt1.this,"One or more answer is incorrect! Can't Submit.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
