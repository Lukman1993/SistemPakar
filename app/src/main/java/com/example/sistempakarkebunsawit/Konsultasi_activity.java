package com.example.sistempakarkebunsawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Konsultasi_activity extends AppCompatActivity {

    private SQLiteDatabase db;
    private MyCustomAdapter dataAdapter = null;
    int globalInc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        //        open database
        DatabaseHelper mDBHelper = new DatabaseHelper(this);
        if (mDBHelper.openDatabase())
            db = mDBHelper.getReadableDatabase();

//        array buat nyimpen daftar gejala
        ArrayList<Gejala> gejalaList = new ArrayList<Gejala>();
        Gejala gejala;
//        ambil data gejala dari database
        String query = "SELECT nama_gejala FROM gejala ORDER BY kode_gejala";
        Cursor cursor = db.rawQuery(query, null);
//        looping untuk memasukkan data gejala kedalam array
        while (cursor.moveToNext()) {
//            ambil gejala dari database dan masukkan ke objek gejala dengan nama gejala dan ceklis default ke false
            gejala = new Gejala(cursor.getString(0), false);
            gejalaList.add(gejala); //masukkan ke array
        }
        cursor.close(); //tutup cursor database

//        adapter untuk daftar gejala biar tampil ke listview
        dataAdapter = new MyCustomAdapter(this, R.layout.list_gejala, gejalaList);
        ListView listView = findViewById(R.id.list_gejala);
        listView.setAdapter(dataAdapter); // set listview dengan dataadapter

        // button diagnosa
        Button myButton = findViewById(R.id.btn_hasil_diagnosa);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer gejalaTerpilih = new StringBuffer();

//                array gejala yang diceklis
                ArrayList<Gejala> gejalaList = dataAdapter.gejalaList;
                for (int i = 0; i < gejalaList.size(); i++) {
                    Gejala gejala = gejalaList.get(i);
                    if (gejala.isSelected()) { // cek apakah gejala diceklis
//                        masukkan ke variabel responseText dengan pemisah tanda tagar #
                        gejalaTerpilih.append(gejala.getNamaGejala()).append("#");
                    }
                }


                // jika tidak ada yang diceklis
                if (gejalaTerpilih.toString().equals("")) {
                    Toast.makeText(Konsultasi_activity.this, "Silakan pilih gejala!", Toast.LENGTH_SHORT).show();

                }else {
                    // tampilkan activity hasil diagnosa
                    Intent myIntent = new Intent(v.getContext(), HasilDiagnosaActivity.class);
                    myIntent.putExtra("HASIL", gejalaTerpilih.toString());
                    startActivity(myIntent);
                }
            }
        });
    }

    //    custom adapter dari listview yang isinya checkbox daftar gejala
    private class MyCustomAdapter extends ArrayAdapter<Gejala> {

        private ArrayList<Gejala> gejalaList;

        MyCustomAdapter(Context context, int textViewResourceId,
                        ArrayList<Gejala> gejalaList) {
            super(context, textViewResourceId, gejalaList);
            this.gejalaList = new ArrayList<Gejala>();
            this.gejalaList.addAll(gejalaList);
        }

        private class ViewHolder {
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_gejala, null);

                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.check_gejala);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Gejala gejala = (Gejala) cb.getTag();
                        gejala.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton checkboxView, boolean isChecked) {
                    Gejala gejala = (Gejala) checkboxView.getTag();

                    if (isChecked)
                    {
                        globalInc++;
                    }
                    else if (!isChecked)
                    {
                        globalInc--;
                    }
                    if(globalInc >= 4) {
                        Toast.makeText(getContext(), "Maaf hanya boleh pilih 3 saja", Toast.LENGTH_LONG).show();
                        checkboxView.setChecked(false);
                        globalInc--;
                    }
                    else
                    {
                        gejala.setSelected(isChecked);
                    }
                    System.out.println(" ---------------    "+globalInc);
                }
            });

            Gejala gejala = gejalaList.get(position);
            holder.name.setText(gejala.getNamaGejala());
            holder.name.setChecked(gejala.isSelected());
            holder.name.setTag(gejala);

            return convertView;
        }
    }
}
