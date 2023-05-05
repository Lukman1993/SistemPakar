package com.example.sistempakarkebunsawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView b_konsul,konsul,pengobatan,penyakit,pestisida,tentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_konsul = (CardView) findViewById(R.id.b_konsultasi);
        konsul   = (CardView) findViewById(R.id.konsultasi);
        pengobatan = (CardView) findViewById(R.id.pengobatan);
        penyakit   = (CardView) findViewById(R.id.penyakit);
        pestisida  = (CardView) findViewById(R.id.pestisida);
        tentang   = (CardView) findViewById(R.id.tentang);


        b_konsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),bantuankonsultasi.class);
                startActivity(i);
            }
        });

        konsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Konsultasi_activity.class);
                startActivity(i);
            }
        });

        pengobatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),pengobatan_activity.class);
                startActivity(i);
            }
        });

        penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DaftarPenyakitActivity.class);
                startActivity(i);
            }
        });

        pestisida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),pestisida_activity.class);
                startActivity(i);
            }
        });

        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Tentang_activity.class);
                startActivity(i);
            }
        });
    }
}