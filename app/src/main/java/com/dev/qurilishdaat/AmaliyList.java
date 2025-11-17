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
    private LinearLayout mashgulot1,mashgulot2,mashgulot3,mashgulot4,mashgulot5,mashgulot6,mashgulot7,mashgulot8,mashgulot9,mashgulot10,mashgulot11,mashgulot12,mashgulot13,mashgulot14,mashgulot15,mashgulot16,mashgulot17,mashgulot18,mashgulot19,mashgulot20,mashgulot21,mashgulot22;
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
        mashgulot10=findViewById(R.id.mashgulot10);
        mashgulot11=findViewById(R.id.mashgulot11);
        mashgulot12=findViewById(R.id.mashgulot12);
        mashgulot13=findViewById(R.id.mashgulot13);
        mashgulot14=findViewById(R.id.mashgulot14);
        mashgulot15=findViewById(R.id.mashgulot15);
        mashgulot16=findViewById(R.id.mashgulot16);
        mashgulot17=findViewById(R.id.mashgulot17);
        mashgulot18=findViewById(R.id.mashgulot18);
        mashgulot19=findViewById(R.id.mashgulot19);
        mashgulot20=findViewById(R.id.mashgulot20);
        mashgulot21=findViewById(R.id.mashgulot21);
        mashgulot22=findViewById(R.id.mashgulot22);


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
        mashgulot10.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy10.pdf");
            startActivity(intent);
        });
        mashgulot11.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy11.pdf");
            startActivity(intent);
        });
        mashgulot12.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy12.pdf");
            startActivity(intent);
        });
        mashgulot13.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy13.pdf");
            startActivity(intent);
        });
        mashgulot14.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy14.pdf");
            startActivity(intent);
        });
        mashgulot15.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy15.pdf");
            startActivity(intent);
        });
        mashgulot16.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy16.pdf");
            startActivity(intent);
        });
        mashgulot17.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy17.pdf");
            startActivity(intent);
        });
        mashgulot18.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy18.pdf");
            startActivity(intent);
        });
        mashgulot19.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy19.pdf");
            startActivity(intent);
        });
        mashgulot20.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy20.pdf");
            startActivity(intent);
        });
        mashgulot21.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy21.pdf");
            startActivity(intent);
        });
        mashgulot22.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilePages.class);
            intent.putExtra("pdfFileName", "amaliy22.pdf");
            startActivity(intent);
        });
    }
}