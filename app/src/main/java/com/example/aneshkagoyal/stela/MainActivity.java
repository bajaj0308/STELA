package com.example.aneshkagoyal.stela;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText log_email;
    EditText log_password;
    FirebaseAuth mfirebaseAuth;
    Button signin;
    TextView signup;
    private FirebaseAuth.AuthStateListener mfirebaseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log_email = findViewById(R.id.log_email);
        log_password = findViewById(R.id.log_pass);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.log_signup);
        mfirebaseAuth = FirebaseAuth.getInstance();
        mfirebaseListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mfirebaseAuth.getCurrentUser();
                if (user != null) {
                    //Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Student_dashboard.class));
                } else {
                    //Toast.makeText(MainActivity.this, "Plz login", Toast.LENGTH_SHORT).show();

                }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String em = log_email.getText().toString();
                String pw = log_password.getText().toString();
                if (em.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Add all info", Toast.LENGTH_SHORT).show();

                } else {
                    mfirebaseAuth.signInWithEmailAndPassword(em, pw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            } else {

                                startActivity(new Intent(MainActivity.this, Student_dashboard.class));
                            }
                        }
                    });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mfirebaseListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mfirebaseAuth.removeAuthStateListener(mfirebaseListener);
    }
}
