package com.example.sistempakarkebunsawit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class bantuankonsultasi extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuankonsultasi);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://www.corteva.id/berita/Cara-Budidaya-Kelapa-Sawit-Hingga-Panen-Terbukti-Panen-Berlimpah.html");
    }
}