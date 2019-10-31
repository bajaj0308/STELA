package com.example.aneshkagoyal.stela;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Course extends BaseActivity {
    EditText cid;
    EditText cp;
    Button reg;
    Map<String,Pair<String,String>>mp;
    FirebaseAuth auth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        cid = findViewById(R.id.course_id);
        cp = findViewById(R.id.course_pass);
        reg = findViewById(R.id.reg_course);
        mp = new HashMap<>();
        mp.put("121",new Pair<String, String>("23","IOT"));
        mp.put("122",new Pair<String, String>("24","CC"));
        ref = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course_id = cid.getText().toString();
                String course_pass = cp.getText().toString();
                if(mp.containsKey(course_id)){
                    Pair<String,String> val = mp.get(course_id);
                    if(course_pass.equals(val.first)){
                        Map<String,Object>map = new HashMap<>();
                        map.put(course_id,val.second);
                        ref.child("Course").updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Course.this,"done",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Course.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    else {
                        Toast.makeText(Course.this,"wrong course credentials",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(Course.this,"wrong course credentials",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
