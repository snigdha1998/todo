package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText signup_user, signup_pass;
    Button signupone, back_to_login;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup_user = findViewById(R.id.signupusername);
        signup_pass = findViewById(R.id.signuppassword);
        signupone = findViewById(R.id.signupuser);
        back_to_login = findViewById(R.id.signuplogin);
        firebaseAuth = FirebaseAuth.getInstance();
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_to_login();
            }
        });
        signupone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_user();
            }
        });
    }

    private void back_to_login() {
        Intent intent = new Intent(Signup.this, Login.class);
        startActivity(intent);
    }

    private void new_user() {
        String Email = signup_user.getText().toString().trim();
        String Password = signup_pass.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            signup_user.setError("email is empty");
            signup_user.requestFocus();

        }
        if (TextUtils.isEmpty(Password)){
            signup_pass.setError("password is wrong");
            signup_pass.requestFocus();
        }
        try{
            firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "user created", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Signup.this, "user not created", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception e){


    }
}}