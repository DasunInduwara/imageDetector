package com.example.image_detector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.image_detector.auth.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {

    ImageButton pStatues, pRuins, pTemples, chatBot;
    private TextView signOutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        pStatues = findViewById(R.id.predict_statues);
        pRuins = findViewById(R.id.predict_temple);
        pTemples = findViewById(R.id.predict_ruins);
        chatBot = findViewById(R.id.chatbot);
        signOutBtn = findViewById(R.id.signOutBtn);
        mAuth = FirebaseAuth.getInstance();

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainMenu.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        pStatues.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        chatBot.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChatBot.class);
            startActivity(intent);
        });

        pRuins.setOnClickListener(v -> {
            Intent intent = new Intent(this, PredictRuins.class);
            startActivity(intent);
        });

        pTemples.setOnClickListener(v -> {
            Intent intent = new Intent(this, PredictStupa.class);
            startActivity(intent);
        });
    }
}