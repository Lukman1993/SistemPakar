package com.example.sistempakarkebunsawit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class pestisida_activity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestisida);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://saraswanti-ash.com/mengendalikan-hama-dan-penyakit-pada-perkebunan-kelapa-sawit/");
    }
}