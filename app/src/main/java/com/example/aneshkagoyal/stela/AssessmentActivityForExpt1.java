package com.example.aneshkagoyal.stela;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AssessmentActivityForExpt1 extends AppCompatActivity {
    Button submit;
    DatabaseReference reff,ref2;
    String nm,br,yr,enroll;
    Map<String,Object> mp;
    int marks;
    String num; //use this for intent number
    String [] email_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_for_expt1);
        ActivityCompat.requestPermissions(AssessmentActivityForExpt1.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        submit = findViewById(R.id.submit_assess);
        mp = new HashMap<String,Object>();
        num = "1";//comment it out
        marks=0;//use this for marks
        email_add = new String[1];

        reff = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref2 = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Course").child("121");
        Map<String,Object>mp2 = new HashMap<>();
        mp2.put("Exp"+num,"0");
        ref2.updateChildren(mp2);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nm = dataSnapshot.child("name").getValue().toString();
                br = dataSnapshot.child("branch").getValue().toString();
                yr = dataSnapshot.child("year").getValue().toString();
                enroll = dataSnapshot.child("eroll").getValue().toString();
                email_add[0] = dataSnapshot.child("email").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //For Interfacing Diagram
                EditText blank1_IFD = (EditText)findViewById(R.id.IFD_blank1);
                EditText blank2_IFD = (EditText)findViewById(R.id.IFD_blank2);
                EditText blank3_IFD = (EditText)findViewById(R.id.IFD_blank3);
                EditText blank4_IFD = (EditText)findViewById(R.id.IFD_blank4);
                String IFD_b1 = blank1_IFD.getText().toString();
                String IFD_b2 = blank2_IFD.getText().toString();
                String IFD_b3 = blank3_IFD.getText().toString();
                String IFD_b4 = blank4_IFD.getText().toString();
                if(IFD_b1.equals("GPIO18")&&IFD_b2.equals("GND")&&IFD_b3.equals("12")&&IFD_b4.equals("6"))
                {
                    marks+=5;
                }

                //For Ques1
                CheckBox ch1_q1= (CheckBox) findViewById(R.id.Q1_checkBox1);
                CheckBox ch2_q1= (CheckBox) findViewById(R.id.Q1_checkBox2);
                CheckBox ch3_q1= (CheckBox) findViewById(R.id.Q1_checkBox3);
                CheckBox ch4_q1= (CheckBox) findViewById(R.id.Q1_checkBox4);
                CheckBox ch5_q1= (CheckBox) findViewById(R.id.Q1_checkBox5);
                if(ch2_q1.isChecked()&&ch3_q1.isChecked()&&!ch1_q1.isChecked()&&!ch4_q1.isChecked()&&!ch5_q1.isChecked()) {
                    marks+=2;
                }

                //for Ques2
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
                    marks+=2;
                }

                //For Ques3
                EditText blank_q3 = (EditText) findViewById(R.id.Q3_blank);
                String blank = blank_q3.getText().toString();
                if(blank.equals("GPIO.output(18,GPIO.HIGH)"))
                {
                    marks+=2;
                }

                //for Ques4
                RadioButton r1_q4 = (RadioButton) findViewById(R.id.Q4_radiob1);
                RadioButton r2_q4 = (RadioButton) findViewById(R.id.Q4_radiob2);
                RadioButton r3_q4 = (RadioButton) findViewById(R.id.Q4_radiob3);
                RadioButton r4_q4 = (RadioButton) findViewById(R.id.Q4_radiob4);
                if(r1_q4.isChecked()&&!(r2_q4.isChecked()||r3_q4.isChecked()||r4_q4.isChecked()))
                {
                    marks+=2;
                }

                //For Ques5
                EditText blank1_q5 = (EditText) findViewById(R.id.Q5_blank1);
                EditText blank2_q5 = (EditText) findViewById(R.id.Q5_blank2);
                String blank1 = blank1_q5.getText().toString();
                String blank2 = blank2_q5.getText().toString();
                if(blank1.equals("GPIO.output(18,GPIO.LOW)")&&blank2.equals("time.sleep(1)"))
                {
                    marks+=2;
                }

                mp.put("Exp"+num,marks);

                ref2.updateChildren(mp);
                //ref2.updateChildren(mp2);
                createpdf(nm,br,yr,enroll,marks);

                Toast.makeText(AssessmentActivityForExpt1.this,nm,Toast.LENGTH_SHORT).show();

                //String sum = createSummary(nm,br,yr,enroll,marks);
                //composeEmail(email_add,"Report of Performance in Exp-"+num,sum);


            }
        });
    }


//    public void composeEmail(String[] addresses, String subject, String body) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//        intent.setData(Uri.parse("mailto:"));
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_TEXT, body);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
//    private String createOrderSummary(String in,int price,boolean ischecked,boolean ischecked1)
//    {
//        String sum="Name: "+in+"\nWant Whipped Cream? "+ischecked+"\nWant Chocolate?"+ischecked1+"\nQuantity:"+numberOfCoffees+"\nTotal:Rs"+price+"\nThank You!";
//        return sum;
//    }


    private String createSummary(String name,String branch,String year,String erol,int marks){
        String summ = "Name: "+name+"\n"+"Branch: "+branch+"\n"+"Year: "+year+"\n"+"Enrollment num: "+erol+"\n"+"Marks: "+marks+"\n";
        return summ;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void createpdf(String name, String branch, String year, String erol, int marks){
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo mypginfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page mypage = document.startPage(mypginfo);
        Paint mypaint = new Paint();
        int x=10;
        int y=11;
        String sum = createSummary(name,branch,year,erol,marks);
        for(String line:sum.split("\n")){
            mypage.getCanvas().drawText(line,x,y,mypaint);
            y+=mypaint.descent()-mypaint.ascent();
        }


        document.finishPage(mypage);
        String filepath = Environment.getExternalStorageDirectory().getPath()+"/myreportcard"+erol+".pdf";
        Log.d("PDF IS HERE",filepath);
        File myfile = new File(filepath);
        try{
            document.writeTo(new FileOutputStream(myfile));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        document.close();
    }
}
