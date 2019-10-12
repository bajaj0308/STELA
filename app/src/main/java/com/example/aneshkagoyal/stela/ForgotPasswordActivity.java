package com.example.aneshkagoyal.stela;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText email_reset;
    Button send_link;
    FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email_reset = findViewById(R.id.forgotpassemail);
        send_link = findViewById(R.id.pass_reset);
        mfirebaseAuth = FirebaseAuth.getInstance();
        send_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfirebaseAuth.sendPasswordResetEmail(email_reset.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this,"sent mail",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this,MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(ForgotPasswordActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
