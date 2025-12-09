package com.dev.qurilishdaat;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FilePages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Intentdan PDF nomi olinadi
        String pdfName = getIntent().getStringExtra("pdfFileName");

        if (pdfName == null || pdfName.isEmpty()) {
            Toast.makeText(this, "PDF nomi berilmagan!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Agar .pdf kengaytmasi bo'lmasa qo'shib qo'yamiz
        if (!pdfName.toLowerCase().endsWith(".pdf")) {
            pdfName = pdfName + ".pdf";
        }

        openPdfFromAssets(pdfName);
    }

    private void openPdfFromAssets(String fileName) {
        File file = new File(getCacheDir(), fileName);

        try {
            // Assetsdan o'qib, cache papkaga nusxalash
            AssetManager assetManager = getAssets();
            InputStream in = assetManager.open(fileName);
            OutputStream out = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();

            // FileProvider orqali URI olish (Android 7.0+ uchun majburiy)
            Uri uri = FileProvider.getUriForFile(this,
                    getPackageName() + ".fileprovider", file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            // Agar PDF ochadigan ilova bo'lsa ochamiz, aks holda xabar
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "PDF ochadigan ilova topilmadi", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "PDF ochilmadi: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}