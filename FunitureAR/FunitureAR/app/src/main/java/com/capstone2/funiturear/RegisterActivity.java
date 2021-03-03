package com.capstone2.funiturear;

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

public class RegisterActivity extends AppCompatActivity {

    EditText userName, email, password, confirmPassword;
    Button signup, signin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.register_username);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.register_confirm_password);
        signup = findViewById(R.id.register_signup);
        signin = findViewById(R.id.register_signin);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = userName.getText().toString().trim();
                String re_email = email.getText().toString().trim();
                String re_password = password.getText().toString().trim();
                String re_confirm = confirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(user_name)){
                    userName.setError("User name is Required.");
                    return;
                }
                if(TextUtils.isEmpty(re_email)){
                    email.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(re_password)){
                    password.setError("Password is Required.");
                    return;
                }
                if(re_password.length() < 6){
                    password.setError("Password Must be >= 6 Characters.");
                    return;
                }
                if(TextUtils.isEmpty(re_confirm)){
                    confirmPassword.setError("Confirm Password is Required.");
                    return;
                }
                if(re_confirm.length() < 6){
                    confirmPassword.setError("Confirm Must be >-6 Characters.");
                    return;
                }
                if(!re_confirm.equals(re_password)){
                    confirmPassword.setError("Confirm Password Must be Match with Password");
                    return;
                }


                //Register the user in Firebase
                firebaseAuth.createUserWithEmailAndPassword(re_email, re_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            FirebaseAuth.getInstance().signOut();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
