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

public class PracticeActivityForExpt3 extends AppCompatActivity {


    Object num;
    int marks=0;
    Button q1_s, q2_s, q3_s, q4_s, q5_s;
    int q1_f=0,q2_f=0,q3_f=0,q4_f=0,q5_f=0;
    DatabaseReference ref,ref2,ref3;
    Object device_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_for_expt_3);
        num=getIntent().getStringExtra("Expt_number");
        Log.v("Num value in PA is ", num.toString());
        Button runYourExperiment3  = (Button) findViewById(R.id.submitInPracticeActivityForExpt3);

        q1_s = (Button) findViewById(R.id.Q1_submit);
        q2_s = (Button) findViewById(R.id.Q2_submit);
        q3_s = (Button) findViewById(R.id.Q3_submit);
        q4_s = (Button) findViewById(R.id.Q4_submit);
        q5_s = (Button) findViewById(R.id.Q5_submit);
        ref = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref2 = FirebaseDatabase.getInstance().getReference().child("Rpi");
        final Map mp = new HashMap<String,Object>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Device"))
                    device_num = dataSnapshot.child("Device").getValue().toString();
                else{
                    Toast.makeText(PracticeActivityForExpt3.this,"Please go to dashboard to register",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PracticeActivityForExpt3.this,Student_dashboard.class));
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
                    Toast.makeText(PracticeActivityForExpt3.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q1_f=0;
                    Toast.makeText(PracticeActivityForExpt3.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }

            }
        });

        q2_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank1_q2 = (EditText)findViewById(R.id.Q2_blank1);
                EditText blank2_q2 = (EditText)findViewById(R.id.Q2_blank2);
                String b1 = blank1_q2.getText().toString();
                String b2 = blank2_q2.getText().toString();
                if(b1.equals("DHT")&&b2.equals("DHT11"))
                {
                    q2_f=1;
                    Toast.makeText(PracticeActivityForExpt3.this,"Right Answer!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    q2_f=0;
                    Toast.makeText(PracticeActivityForExpt3.this,"Wrong Answer",Toast.LENGTH_SHORT).show();

                }
            }
        });

        q3_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank_q3 = (EditText) findViewById(R.id.Q3_blank);
                String blank = blank_q3.getText().toString();
                String option1 = "hum,temp=Adafruit_DHT.read_retry(sensor,pin)";
                if(blank.equals(option1))
                {
                    q3_f=1;
                    Toast.makeText(PracticeActivityForExpt3.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q3_f=0;
                    Toast.makeText(PracticeActivityForExpt3.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
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
                if(r3_q4.isChecked()&&!(r1_q4.isChecked()||r2_q4.isChecked()||r4_q4.isChecked()))
                {
                    q4_f=1;
                    Toast.makeText(PracticeActivityForExpt3.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q4_f=0;
                    Toast.makeText(PracticeActivityForExpt3.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });
        q5_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText blank1_q5 = (EditText) findViewById(R.id.Q5_blank1);
                String blank1 = blank1_q5.getText().toString();
                if(blank1.equals("time.sleep(1)"))
                {
                    q5_f=1;
                    Toast.makeText(PracticeActivityForExpt3.this,"Right Answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    q5_f=0;
                    Toast.makeText(PracticeActivityForExpt3.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
            }
        });
        runYourExperiment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //num is the experiment number
                //TODO : put the experiment number on firebase

                if(q1_f==1&&q2_f==1&&q3_f==1&&q4_f==1&&q5_f==1) {
                    mp.put(device_num, num);
                    ref2.updateChildren(mp).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(PracticeActivityForExpt3.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ref3 = FirebaseDatabase.getInstance().getReference().child("Rpi").child(device_num.toString());
                    ref3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String temp_humid = dataSnapshot.getValue().toString();
                            Log.d("val_of_temp",temp_humid);
//                            while(temp_humid.equals(num)){
//                                temp_humid = dataSnapshot.getValue().toString();
//
//                            }
                            if(!temp_humid.equals(num) && !temp_humid.equals("0")){
                                Toast.makeText(PracticeActivityForExpt3.this,"temperature and humidity values are :"+temp_humid,Toast.LENGTH_SHORT).show();
                                mp.put(device_num, "0");
                                ref2.updateChildren(mp);


                            }






                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    //TODO: update data on firebase and edit the following toast message
                    Toast.makeText(PracticeActivityForExpt3.this,"Submitted successfully",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(PracticeActivityForExpt3.this,"One or more answer is incorrect! Can't Submit.",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
