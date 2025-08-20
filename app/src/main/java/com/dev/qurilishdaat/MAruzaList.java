package com.dev.qurilishdaat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MAruzaList extends AppCompatActivity {
    private LinearLayout maruza1,maruza2,maruza3,maruza4,maruza5,maruza6,maruza7,maruza8,maruza9,maruza10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maruza_list);
        maruza1=findViewById(R.id.maruza1);
        maruza2=findViewById(R.id.maruza2);
        maruza3=findViewById(R.id.maruza3);
        maruza4=findViewById(R.id.maruza4);
        maruza5=findViewById(R.id.maruza5);
        maruza6=findViewById(R.id.maruza6);
        maruza7=findViewById(R.id.maruza7);
        maruza8=findViewById(R.id.maruza8);
        maruza9=findViewById(R.id.maruza9);
        maruza10=findViewById(R.id.maruza10);

        maruza1.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat1.pdf");
            startActivity(intent);
        });
        maruza2.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat2.pdf");
            startActivity(intent);
        });
        maruza3.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat3.pdf");
            startActivity(intent);
        });
        maruza4.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat4.pdf");
            startActivity(intent);
        });
        maruza5.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat5.pdf");
            startActivity(intent);
        });
        maruza6.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat6.pdf");
            startActivity(intent);
        });
        maruza7.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat7.pdf");
            startActivity(intent);
        });
        maruza8.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat8.pdf");
            startActivity(intent);
        });
        maruza9.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat9.pdf");
            startActivity(intent);
        });
        maruza10.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "nazorat10.pdf");
            startActivity(intent);
        });
    }
}