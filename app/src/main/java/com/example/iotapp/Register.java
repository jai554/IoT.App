package com.example.iotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView banner;
    private Button buttonRegister;
    private EditText editTextFName, editTextPhoneNo, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        buttonRegister = (Button) findViewById(R.id.txtRegister);
        buttonRegister.setOnClickListener(this);

        editTextFName = (EditText) findViewById(R.id.LoginEmail);
        editTextPhoneNo = (EditText) findViewById(R.id.PhoneNo);
        editTextEmail = (EditText) findViewById(R.id.LoginPassword);
        editTextPassword = (EditText) findViewById(R.id.Password);

        progressBar = (ProgressBar) findViewById(R.id.loading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.txtRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String fName = editTextFName.getText().toString().trim();
        String phoneNo = editTextPhoneNo.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(fName.isEmpty()){
            editTextFName.setError("Full name is required!");
            editTextFName.requestFocus();
            return;
        }

        if(phoneNo.isEmpty()){
            editTextPhoneNo.setError("Valid phone Number is required!");
            editTextPhoneNo.requestFocus();
            return;
        }

        if(phoneNo.length() < 9){
            editTextPhoneNo.setError("Valid phone Number is required!");
            editTextPhoneNo.requestFocus();
            return;
        }

        if(!Patterns.PHONE.matcher(phoneNo).matches()){
            editTextPhoneNo.setError("Valid phone number is only numbers!");
            editTextPhoneNo.requestFocus();
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is empty!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Required 6 characters or more!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User user = new User(fName, phoneNo, email);

                        FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Register.this, "User has been successfully registered!", Toast.LENGTH_LONG).show();

                                }else{
                                    Toast.makeText(Register.this, "User registration failed!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(Register.this, "User registration failed!", Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });
    }
}