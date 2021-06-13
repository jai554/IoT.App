package com.example.iotapp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button reset;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.LoginEmail);
        reset = (Button) findViewById(R.id.reset);
        progressBar = (ProgressBar) findViewById(R.id.loading);

        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String emailString = email.getText().toString().trim();

        if(emailString.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(emailString).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Password reset email is sent!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "Error occurred! Please try again!", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}