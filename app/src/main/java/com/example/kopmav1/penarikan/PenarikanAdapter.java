package com.example.kopmav1.penarikan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kopmav1.R;

import java.util.ArrayList;

public class PenarikanAdapter extends RecyclerView.Adapter<PenarikanAdapter.PenarikanViewHolder> {
    private ArrayList<PenarikanItem> penarikanList;

    public PenarikanAdapter(ArrayList<PenarikanItem> penarikanList) {
        this.penarikanList = penarikanList;
    }

    @NonNull
    @Override
    public PenarikanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penarikan, parent, false);
        PenarikanViewHolder penarikanViewHolder = new PenarikanViewHolder(v);

        return penarikanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PenarikanViewHolder holder, int position) {
        PenarikanItem currentItem = penarikanList.get(position);

        holder.textIdTabungan.setText(currentItem.getId_tabungan());
        holder.textIdAnggota.setText(currentItem.getId_anggota());
        holder.textIdNama.setText(currentItem.getNama());
        holder.textPenarikan.setText(currentItem.getPenarikan());
    }

    @Override
    public int getItemCount() {
        return penarikanList.size();
    }

    public class PenarikanViewHolder extends RecyclerView.ViewHolder{
        TextView textIdTabungan, textIdAnggota, textIdNama, textPenarikan;

        public PenarikanViewHolder(@NonNull View itemView) {
            super(itemView);

            textIdTabungan = itemView.findViewById(R.id.textPenarikanIdTabungan);
            textIdAnggota = itemView.findViewById(R.id.textPenarikanIdAnggota);
            textIdNama = itemView.findViewById(R.id.textPenarikanNama);
            textPenarikan = itemView.findViewById(R.id.textPenarikanJumlah);
        }
    }
}
