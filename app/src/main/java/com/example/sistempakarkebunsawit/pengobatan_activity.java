package com.example.sistempakarkebunsawit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class pengobatan_activity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengobatan);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://www.corteva.id/berita/Penyakit-Pada-Tanaman-Kelapa-Sawit-dan-Cara-Mencegahnya.html");
    }
}