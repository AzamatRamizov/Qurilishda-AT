package com.dev.qurilishdaat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroActivity extends AppCompatActivity {
    Button btnEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);
        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(v -> {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish();
        });
    }
}