package com.example.kopmav1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ExampleViewHolder> {
    private ArrayList<ModelData> mExampleList;
    private Context mContext;

    public static class  ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView, mTextView2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.TextView);
            mTextView2 = itemView.findViewById(R.id.TextView2);
        }
    }

    public AdapterData( ArrayList<ModelData> Listt ) {
        this.mExampleList = Listt;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ModelData currentitem = mExampleList.get(position);

        holder.mTextView.setText(currentitem.getId_anggota());
        holder.mTextView2.setText(currentitem.getSetoran());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
