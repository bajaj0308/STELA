package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SignUpActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText eroll;
    EditText reg_name;
    EditText branch;
    EditText year;
    Button signup;
    TextView signin;
    FirebaseAuth mfirebaseAuth;
    DatabaseReference ref;
    EditText mob;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_pass);
        eroll = findViewById(R.id.reg_enroll);
        reg_name = findViewById(R.id.reg_name);
        branch = findViewById(R.id.reg_branch);
        year = findViewById(R.id.reg_year);
        signup = findViewById(R.id.sign_up);
        signin = findViewById(R.id.reg_sign_in);
        mob = findViewById(R.id.reg_mob);
        address = findViewById(R.id.reg_addr);
        mfirebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Student");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String em = email.getText().toString();
                String pw = password.getText().toString();
                final String er = eroll.getText().toString();
                final String nm = reg_name.getText().toString();
               final String br = branch.getText().toString();
                final String yr = year.getText().toString();
                final String mobile = mob.getText().toString();
                final String add = address.getText().toString();
                mfirebaseAuth = FirebaseAuth.getInstance();
                if(em.isEmpty()||pw.isEmpty()||er.isEmpty()||nm.isEmpty()||br.isEmpty()||yr.isEmpty()||mobile.isEmpty()||add.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Add all info",Toast.LENGTH_SHORT).show();

                }
                else{
                    mfirebaseAuth.createUserWithEmailAndPassword(em,pw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                            else{
                                UserStudent student = new UserStudent(
                                        nm,er,br,yr,em,mobile,add
                                );
                                ref.child(mfirebaseAuth.getCurrentUser().getUid()).setValue(student);

                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                finish();
                            }
                        }
                    });

                }



            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });




    }

}
