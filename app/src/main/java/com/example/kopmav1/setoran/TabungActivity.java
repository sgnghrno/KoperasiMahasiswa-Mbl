package com.example.kopmav1.setoran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kopmav1.ApiUrl;
import com.example.kopmav1.R;
import com.example.kopmav1.TambungActivty;
import com.example.kopmav1.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabungActivity extends AppCompatActivity {
    private ArrayList<com.example.kopmav1.ModelData> ModelData;

    Button btnInsert, btnKembali;

    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    //    keperluan recyclerview
    RecyclerView setoranRecyclerView;
    SetoranAdapter setoranAdapter;
    RecyclerView.LayoutManager setoranLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabung);

        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        btnInsert = findViewById(R.id.btn_insert_saldo);
        btnKembali = findViewById(R.id.btn_kembali_saldo);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabungActivity.this, TambungActivty.class);
                startActivity(intent);
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(TabungActivity.this, HomeActivity.class);
                startActivity(hapus);
                finish();
            }
        });

        loadSetoran();
    }

    private void loadSetoran() {
        progressDialog.setMessage("Memperbarui informasi...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest setoranRequest = new StringRequest(Request.Method.GET, ApiUrl.TABUNGAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONArray arrayResponse = new JSONArray(response);
                    ArrayList<SetoranItem> setoranList = new ArrayList<>();

                    for (int i = 0; i < arrayResponse.length(); i++){
                        JSONObject setoran = arrayResponse.getJSONObject(i);

                        String id_tabungan = setoran.getString("id_tabungan");
                        String id_anggota = setoran.getString("id_anggota");
                        String nama = setoran.getString("nama");
                        String nilaiSetoran = setoran.getString("setoran");

                        setoranList.add(new SetoranItem(id_tabungan, id_anggota, nilaiSetoran, nama));
                    }

                    setoranRecyclerView = findViewById(R.id.rvSetoran);
                    setoranRecyclerView.setHasFixedSize(true);
                    setoranLayoutManager = new LinearLayoutManager(getApplicationContext());
                    setoranAdapter = new SetoranAdapter(setoranList);

                    setoranRecyclerView.setLayoutManager(setoranLayoutManager);
                    setoranRecyclerView.setAdapter(setoranAdapter);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(setoranRequest);
    }
}