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

import static android.util.Log.*;

public class PracticeActivityForExpt4 extends AppCompatActivity {

    Object num;
    int marks=0;
    Button q1_s, q2_s, q3_s, q4_s, q5_s;
    int q1_f=0,q2_f=0,q3_f=0,q4_f=0,q5_f=0;
    DatabaseReference ref,ref2,ref3;
    Object device_num;
    Map mp = new HashMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_for_expt4);
        num=getIntent().getStringExtra("Expt_number");
        v("Num value in PA is ", num.toString());
        Button runYourExperiment4  = (Button) findViewById(R.id.submitInPracticeActivityForExpt4);

        q1_s = (Button) findViewById(R.id.Q1_submit);
        q2_s = (Button) findViewById(R.id.Q2_submit);
        q3_s = (Button) findViewById(R.id.Q3_submit);
        q4_s = (Button) findViewById(R.id.Q4_submit);
        q5_s = (Button) findViewById(R.id.Q5_submit);
        ref = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref2 = FirebaseDatabase.getInstance().getReference().child("Rpi");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Device"))
                    device_num = dataSnapshot.child("Device").getValue().toString();
                else{
                    Toast.makeText(PracticeActivityForExpt4.this,"Please go to dashboard to register",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PracticeActivityForExpt4.this,Student_dashboard.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        q1_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ch1_q1= (CheckBox) findViewById(R.id.Q1_checkBox1);
                CheckBox ch2_q1= (CheckBox) findViewById(R.id.Q1_checkBox2);
                CheckBox ch3_q1= (CheckBox) findViewById(R.id.Q1_checkBox3);
                CheckBox ch4_q1= (CheckBox) findViewById(R.id.Q1_checkBox4);
                CheckBox ch5_q1= (CheckBox) findViewById(R.id.Q1_checkBox5);
                if(ch2_q1.isChecked()&&ch5_q1.isChecked()&&!ch1_q1.isChecked()&&!ch3_q1.isChecked()&&!ch4_q1.isChecked()) {
                    q1_f = 1;
                    Toast.makeText(PracticeActivityForExpt4.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q1_f=0;
                    Toast.makeText(PracticeActivityForExpt4.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
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
                EditText blank5_q2 = (EditText)findViewById(R.id.Q2_blank5);
                String b1 = blank1_q2.getText().toString();
                String b2 = blank2_q2.getText().toString();
                String b3 = blank3_q2.getText().toString();
                String b4 = blank4_q2.getText().toString();
                String b5 = blank5_q2.getText().toString();
                if(b1.equals("GPIO")&&b2.equals("BCM")&&b3.equals("18")&&b4.equals("up")&&b5.equals("IN"))
                {
                    q2_f=1;
                    Toast.makeText(PracticeActivityForExpt4.this,"Right Answer!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    q2_f=0;
                    Toast.makeText(PracticeActivityForExpt4.this,"Wrong Answer",Toast.LENGTH_SHORT).show();

                }
            }
        });

        q3_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank1_q3 = (EditText) findViewById(R.id.Q3_blank1);
                EditText blank2_q3 = (EditText) findViewById(R.id.Q3_blank2);
                String b1 = blank1_q3.getText().toString();
                String b2 = blank2_q3.getText().toString();
                String option1 = "GPIO.input(IR)";
                String option2 = "time.sleep(2)";
                if(b1.equals(option1)&&b2.equals(option2))
                {
                    q3_f=1;
                    Toast.makeText(PracticeActivityForExpt4.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q3_f=0;
                    Toast.makeText(PracticeActivityForExpt4.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });

        q4_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank_q4 = (EditText) findViewById(R.id.Q4_blank);
                String b1 = blank_q4.getText().toString();
                String option1 = "c-1";
                String option2 = "c--";
                if(b1.equals(option1) || b1.equals(option2))
                {
                    q4_f=1;
                    Toast.makeText(PracticeActivityForExpt4.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q4_f=0;
                    Toast.makeText(PracticeActivityForExpt4.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });

        q5_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton r1_q5 = (RadioButton) findViewById(R.id.Q5_radiob1);
                RadioButton r2_q5 = (RadioButton) findViewById(R.id.Q5_radiob2);
                RadioButton r3_q5 = (RadioButton) findViewById(R.id.Q5_radiob3);
                RadioButton r4_q5 = (RadioButton) findViewById(R.id.Q5_radiob4);
                if(r2_q5.isChecked()&&!(r1_q5.isChecked()||r3_q5.isChecked()||r4_q5.isChecked()))
                {
                    q5_f=1;
                    Toast.makeText(PracticeActivityForExpt4.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q5_f=0;
                    Toast.makeText(PracticeActivityForExpt4.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });
        runYourExperiment4.setOnClickListener(new View.OnClickListener() {
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
                    Log.v("Num value is ", "......................");
                    Log.v("Num value is ", mp +"");
                   ref2.updateChildren(mp).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(PracticeActivityForExpt4.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(PracticeActivityForExpt4.this,"One or more answer is incorrect! Can't Submit.",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
