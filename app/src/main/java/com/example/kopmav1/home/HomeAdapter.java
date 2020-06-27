package com.example.kopmav1.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kopmav1.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<HomeItem> saldoList;

    public HomeAdapter(ArrayList<HomeItem> saldoList) {
        this.saldoList = saldoList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saldo_anggota, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(v);

        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeItem currentItem = saldoList.get(position);

        holder.textIdAnggota.setText(currentItem.getId_anggota());
        holder.textNamaAnggota.setText(currentItem.getNama_anggota());
        holder.textTotSetoran.setText(currentItem.getTotal_setoran());
        holder.textTotPenarikan.setText(currentItem.getTotal_penarikan());
        holder.textTotSaldo.setText(currentItem.getTotal_saldo());
    }

    @Override
    public int getItemCount() {
        return saldoList.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView textIdAnggota, textNamaAnggota, textTotSetoran, textTotPenarikan, textTotSaldo;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            textIdAnggota = itemView.findViewById(R.id.textSaldoIdAnggota);
            textNamaAnggota = itemView.findViewById(R.id.textSaldoNamaAnggota);
            textTotSetoran = itemView.findViewById(R.id.textSaldoSetoran);
            textTotPenarikan = itemView.findViewById(R.id.textSaldoPenarikan);
            textTotSaldo = itemView.findViewById(R.id.textSaldoTotal);
        }
    }
}
