package com.dev.qurilishdaat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AmaliyList extends AppCompatActivity {
    private LinearLayout mashgulot1,mashgulot2,mashgulot3,mashgulot4,mashgulot5,mashgulot6,mashgulot7,mashgulot8,mashgulot9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaliy_list);
        mashgulot1=findViewById(R.id.mashgulot1);
        mashgulot2=findViewById(R.id.mashgulot2);
        mashgulot3=findViewById(R.id.mashgulot3);
        mashgulot4=findViewById(R.id.mashgulot4);
        mashgulot5=findViewById(R.id.mashgulot5);
        mashgulot6=findViewById(R.id.mashgulot6);
        mashgulot7=findViewById(R.id.mashgulot7);
        mashgulot8=findViewById(R.id.mashgulot8);
        mashgulot9=findViewById(R.id.mashgulot9);

        mashgulot1.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy1.pdf");
            startActivity(intent);
        });
        mashgulot2.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy2.pdf");
            startActivity(intent);
        });
        mashgulot3.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy3.pdf");
            startActivity(intent);
        });
        mashgulot4.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy4.pdf");
            startActivity(intent);
        });
        mashgulot5.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy5.pdf");
            startActivity(intent);
        });
        mashgulot6.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy6.pdf");
            startActivity(intent);
        });
        mashgulot7.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy7.pdf");
            startActivity(intent);
        });
        mashgulot8.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy8.pdf");
            startActivity(intent);
        });
        mashgulot9.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy9.pdf");
            startActivity(intent);
        });
    }
}