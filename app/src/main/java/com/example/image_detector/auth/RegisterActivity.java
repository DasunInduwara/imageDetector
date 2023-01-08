package com.example.image_detector.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.image_detector.MainMenu;
import com.example.image_detector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passEditText, conPassEditText;
    private AppCompatButton registerBtn;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passwordEditText);
        conPassEditText = findViewById(R.id.passwordConfirmEditText);
        registerBtn = findViewById(R.id.registerBtn);

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setCancelable(false);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEditText.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }else if (passEditText.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if (conPassEditText.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter confirm password", Toast.LENGTH_SHORT).show();
                }else if (!passEditText.getText().toString().equals(conPassEditText.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Password Mismatched!", Toast.LENGTH_SHORT).show();
                }else{
                    mProgressDialog.show();
                    mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), conPassEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressDialog.dismiss();
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, MainMenu.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Register unsuccessful!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}