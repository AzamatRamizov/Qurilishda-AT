package com.dev.qurilishdaat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Natija extends AppCompatActivity {
    private List<TestModel> testList;
    private List<Integer> userAnswers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natija);
        String jsonTestList = getIntent().getStringExtra("testList");
        testList = new Gson().fromJson(jsonTestList, new TypeToken<List<TestModel>>(){}.getType());
        userAnswers = (List<Integer>) getIntent().getSerializableExtra("userAnswers");

        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String group = getIntent().getStringExtra("group");
        String variant = getIntent().getStringExtra("variant");
        String baho="";

        int correctCount = 0;
        for (int i = 0; i < testList.size(); i++) {
            if (userAnswers.get(i) == testList.get(i).getCorrectAnswerIndex()) {
                correctCount++;
            }
        }
        if(correctCount>=0 && correctCount<5){
            baho="1";
        }else if(correctCount>=5 && correctCount<10){
            baho="2";
        }else if(correctCount>=10 && correctCount<15){
            baho="3";
        }
        else if(correctCount>=15 && correctCount<20){
            baho="4";
        }
        else if(correctCount>=20 && correctCount<=25) {
            baho = "5";
        }
        // Natijani ko‘rsatish
        TextView resultText = findViewById(R.id.result_text);
        resultText.setText("Siz " + testList.size() + " ta testdan " + correctCount + " tasiga to‘g‘ri javob berdingiz. Sizning baho: "+baho);

        // ========================
        // ✅ Har bir testni saqlash
        // ========================
        SharedPreferences prefs = getSharedPreferences("TestHistory", MODE_PRIVATE);
        int attemptCount = prefs.getInt("attemptCount", 0);

        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        String resultData = "Sana: " + datetime +
                "\nIsm: " + name +
                "\nFamiliya: " + surname +
                "\nGuruh: " + group +
                "\nVariant: " + variant +
                "\nBaho: " + baho +
                "\nNatija: " + correctCount + "/" + testList.size();

        int newAttempt = attemptCount + 1;
        prefs.edit()
                .putString("attempt_" + newAttempt, resultData)
                .putInt("attemptCount", newAttempt)
                .apply();

        String resultMessage = correctCount + "/" + testList.size();
        sendResultToTelegramWithOkHttp(name + " " + surname, group, resultMessage, variant, baho);
    }
    private void sendResultToTelegramWithOkHttp(String fullName, String group, String result, String variant, String baho) {
        OkHttpClient client = new OkHttpClient();

        String botToken = "7974033741:AAGUp-xAKYMTgE79T93ctKsaFiPVGJER6d8";
        String chatId = "1681111402";
        String message = "📨 *Yangi test natijasi*\n\n"
                + "*F.I.Sh:* " + fullName + "\n"
                + "*Guruh:* " + group + "\n"
                + "*Natija:* " + result + "\n"
                + "*Variant:* " + variant + "\n"
                + "*Baho:* " + baho + "\n"
                + "*Sana:* " + new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

        try {
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String url = "https://api.telegram.org/bot" + botToken +
                    "/sendMessage?chat_id=" + chatId +
                    "&text=" + encodedMessage +
                    "&parse_mode=Markdown";

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("TelegramBot", "Yuborishda xatolik: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.d("TelegramBot", "✅ Natija yuborildi");
                    } else {
                        Log.e("TelegramBot", "❌ Telegram javobi: " + response.code());
                    }
                }
            });

        } catch (Exception e) {
            Log.e("TelegramBot", "Xatolik: " + e.getMessage());
            e.printStackTrace();
        }
    }
}