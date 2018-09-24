package com.example.fshmo.businesscard;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LTAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button instagramBtn = findViewById(R.id.social_instagram);
        instagramBtn.setBackgroundResource(R.drawable.instagram_logo);
        instagramBtn.setOnClickListener(v -> openWebPage("https://www.instagram.com/fedorthemaker/"));

        final Button facebookBtn = findViewById(R.id.social_facebook);
        facebookBtn.setBackgroundResource(R.drawable.facebook_logo);
        facebookBtn.setOnClickListener(v -> openWebPage("https://www.facebook.com/fedor.shmorgilov"));

        final Button telegramBtn = findViewById(R.id.social_telegram);
        telegramBtn.setBackgroundResource(R.drawable.telegram_logo);
        telegramBtn.setOnClickListener(v -> openWebPage("https://t.me/Iyanamas"));

    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        final String errorMessage = "No app compatible";
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.d(LTAG, "Navigated to: " + url);
            startActivity(intent);
        } else {
            Log.i(LTAG, errorMessage);
            showToast(errorMessage);
        }
    }

    private void showToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }

    private void composeEmail(String[] addresses, @NonNull final String subject, @NonNull final String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        final String errorMessage = "No email app";
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else {
            Log.i(LTAG, errorMessage);
            showToast(errorMessage);
        }
    }
}
