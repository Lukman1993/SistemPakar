package com.example.sistempakarkebunsawit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public AdapterList(Konsultasi_activity konsultasi_activity, ArrayList<HashMap<String, String>> list_data) {
        this.context = konsultasi_activity;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gejala, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txthape.setText(list_data.get(position).get("nama_gejala"));
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox txthape;
        ImageView imghape;

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}