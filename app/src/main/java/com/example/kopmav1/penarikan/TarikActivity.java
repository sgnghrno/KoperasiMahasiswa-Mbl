package com.example.kopmav1.penarikan;

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
import com.example.kopmav1.TamrikActivity;
import com.example.kopmav1.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TarikActivity extends AppCompatActivity {
    Button btnInsert, btnKembali;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    RecyclerView penarikanRecyclerView;
    PenarikanAdapter penarikanAdapter;
    RecyclerView.LayoutManager penarikanLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik);

        btnInsert = findViewById(R.id.btn_insert_penarikan);
        btnKembali = findViewById(R.id.btn_kembali_penarikan);
        progressDialog = new ProgressDialog(TarikActivity.this);
        requestQueue = Volley.newRequestQueue(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarikActivity.this, TamrikActivity.class);
                startActivity(intent);
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(TarikActivity.this, HomeActivity.class);
                startActivity(hapus);
                finish();
            }
        });

        loadPenarikan();
    }

    private void loadPenarikan(){
        progressDialog.setMessage("Memperbarui informasi...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest penarikanRequest = new StringRequest(Request.Method.GET, ApiUrl.TARIKAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try{
                    JSONArray arrayResponse = new JSONArray(response);
                    ArrayList<PenarikanItem> penarikanList = new ArrayList<>();

                    for (int i = 0; i < arrayResponse.length(); i++){
                        JSONObject setoran = arrayResponse.getJSONObject(i);

                        String id_tabungan = setoran.getString("id_tabungan");
                        String id_anggota = setoran.getString("id_anggota");
                        String nama = setoran.getString("nama");
                        String nilaiPenarikan = setoran.getString("penarikan");

                        penarikanList.add(new PenarikanItem(id_tabungan, id_anggota, nama, nilaiPenarikan));
                    }

                    penarikanRecyclerView = findViewById(R.id.rvPenarikan);
                    penarikanRecyclerView.setHasFixedSize(true);
                    penarikanLayoutManager = new LinearLayoutManager(getApplicationContext());
                    penarikanAdapter = new PenarikanAdapter(penarikanList);

                    penarikanRecyclerView.setLayoutManager(penarikanLayoutManager);
                    penarikanRecyclerView.setAdapter(penarikanAdapter);

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

        requestQueue.add(penarikanRequest);
    }
}
