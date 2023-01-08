package com.example.image_detector.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.image_detector.MainMenu;
import com.example.image_detector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private AppCompatButton registerBtn, loginBtn;
    private EditText emailEditText, passEditText;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setCancelable(false);

        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passwordEditText);
        
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.continueBtn);
        
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEditText.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter valid email ", Toast.LENGTH_SHORT).show();
                }else if(passEditText.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter valid password ", Toast.LENGTH_SHORT).show();
                }else{
                    mProgressDialog.show();
                    mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressDialog.dismiss();
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                                startActivity(intent);
                            }else{
                                Log.w("Login", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}