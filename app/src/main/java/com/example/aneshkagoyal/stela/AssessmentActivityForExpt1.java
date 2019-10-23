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
    DatabaseReference reff;
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
        marks=10;//use this for marks
        email_add = new String[1];

        reff = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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


                mp.put("Exp"+num,marks);
                reff.updateChildren(mp);
                createpdf(nm,br,yr,enroll,marks);

                Toast.makeText(AssessmentActivityForExpt1.this,nm,Toast.LENGTH_SHORT).show();

                //String sum = createSummary(nm,br,yr,enroll,marks);
                //composeEmail(email_add,"Report of Performance in Exp-"+num,sum);


            }
        });
    }
    public void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
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
        String filepath = Environment.getExternalStorageDirectory().getPath()+"/myreportcard1.pdf";
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
