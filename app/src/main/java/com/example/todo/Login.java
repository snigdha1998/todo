package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firestore.v1.TargetOrBuilder;

public class Login extends AppCompatActivity {
    EditText loginemail, loginpass;
    Button login, signup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginemail = findViewById(R.id.Loginusername);
        loginpass = findViewById(R.id.Loginpassword);
        login = findViewById(R.id.userlogin);
        signup = findViewById(R.id.usersignup);
        firebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceed();
            }
        });

    }
        private void proceed () {
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }

        private void loginuser () {
            final String logEmail = loginemail.getText().toString().trim();
            final String logpass = loginpass.getText().toString().trim();
            try {
                firebaseAuth.signInWithEmailAndPassword(logEmail, logpass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String username = loginemail.getText().toString().trim();
                            String password = loginpass.getText().toString().trim();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                           startActivity(intent);

                        } else {
                            Toast.makeText(Login.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

            }
        }

            }



