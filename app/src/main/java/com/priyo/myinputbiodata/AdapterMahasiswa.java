package com.priyo.myinputbiodata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.ViewHolder> {
    private static ClickListener clickListener;
    private List<Mahasiswa> daftar_mhs;

    @Override
    public AdapterMahasiswa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.detail_mhs, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterMahasiswa.ViewHolder holder, int position) {
        Mahasiswa data = daftar_mhs.get(position);
        holder.txt1.setText(data.getNim());
        holder.txt2.setText(data.getNama());
        holder.txt3.setText(data.getAlamat());
        holder.img1.setImageResource(data.getJns_ke() ? R.drawable.man : R.drawable.woman);
    }

    @Override
    public int getItemCount() {
        return daftar_mhs.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView img1;
        public TextView txt1,txt2, txt3;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            layout = itemView;
            img1 = itemView.findViewById(R.id.img1);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public AdapterMahasiswa(List<Mahasiswa> mhs) {
        daftar_mhs = mhs;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        AdapterMahasiswa.clickListener = clickListener;
    }

    public Mahasiswa getItem(int position) {
        return daftar_mhs.get(position);
    }

}
