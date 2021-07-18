package com.example.TVK.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TVK.R;

public class AppStartActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
        btnLogin = findViewById(R.id.btnlogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(AppStartActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(AppStartActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}