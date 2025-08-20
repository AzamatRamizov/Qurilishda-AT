package com.dev.qurilishdaat;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {
    private TextView scrollingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about);
        scrollingText = findViewById(R.id.scrollingText);

        // Ekran balandligi
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Translate animatsiyasi: pastdan yuqoriga
        TranslateAnimation animation = new TranslateAnimation(
                0, 0,
                screenHeight, -screenHeight
        );
        animation.setDuration(20000); // davomiyligi 10 soniya
        animation.setFillAfter(true);

        // Animatsiya tugagach avtomatik orqaga qaytish
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Boshlanishida hech nima qilinmaydi
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish(); // Oynani yopish (orqaga qaytish)
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Takrorlanmaydi
            }
        });

        scrollingText.startAnimation(animation);
    }
}