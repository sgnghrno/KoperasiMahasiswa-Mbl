package com.example.kopmav1.setoran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kopmav1.R;

import java.util.ArrayList;

public class SetoranAdapter extends RecyclerView.Adapter<SetoranAdapter.SetoranViewHolder> {
    private ArrayList<SetoranItem> setoranList;

    public SetoranAdapter(ArrayList<SetoranItem> setoranList) {
        this.setoranList = setoranList;
    }

    @NonNull
    @Override
    public SetoranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setoran, parent, false);
        SetoranViewHolder setoranViewHolder = new SetoranViewHolder(v);

        return setoranViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetoranViewHolder holder, int position) {
        SetoranItem currentItem = setoranList.get(position);

        holder.textIdTabungan.setText(currentItem.getId_tabungan());
        holder.textIdAnggota.setText(currentItem.getId_anggota());
        holder.textNama.setText(currentItem.getNama());
        holder.textSetoran.setText(currentItem.getSetoran());
    }

    @Override
    public int getItemCount() {
        return setoranList.size();
    }


    public class SetoranViewHolder extends RecyclerView.ViewHolder {
        TextView textIdTabungan, textIdAnggota, textNama, textSetoran;

        public SetoranViewHolder(@NonNull View itemView) {
            super(itemView);

            textIdTabungan = itemView.findViewById(R.id.textSetoranIdTabungan);
            textIdAnggota = itemView.findViewById(R.id.textSetoranIdAnggota);
            textNama = itemView.findViewById(R.id.textSetoranNama);
            textSetoran = itemView.findViewById(R.id.textSetoranJumlah);
        }
    }
}
