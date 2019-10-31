package com.example.aneshkagoyal.stela;

import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterDeviceActivity extends BaseActivity {
EditText dev_num;
Button submit;
DatabaseReference ref;
DatabaseReference ref2;
ValueEventListener val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        dev_num = findViewById(R.id.device_num);
        submit = findViewById(R.id.reg_dev);
        ref = FirebaseDatabase.getInstance().getReference().child("Rpi");
        ref2 = FirebaseDatabase.getInstance().getReference().child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String num = dev_num.getText().toString();
                val=ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(num)){
                          ref2.child("Device").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if(task.isSuccessful()){
                                      Toast.makeText(RegisterDeviceActivity.this,"Done",Toast.LENGTH_SHORT).show();
                                  }
                                  else {
                                      Toast.makeText(RegisterDeviceActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                  }
                              }
                          });

                        }
                        else {
                            Toast.makeText(RegisterDeviceActivity.this,"Wrong Device",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        ref.removeEventListener(val);

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
