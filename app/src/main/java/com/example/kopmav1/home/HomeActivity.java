package com.example.kopmav1.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.kopmav1.SessionManager;
import com.example.kopmav1.setoran.TabungActivity;
import com.example.kopmav1.penarikan.TarikActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageButton tabung;
    private ImageButton tarik;
    Button lgout;

    //    session manager dipakai untuk logout
    SessionManager sessionManager;

    //    request queue untuk volley
    RequestQueue requestQueue;
    ProgressDialog progressDialog;

    //    keperluan recyclerview
    RecyclerView homeRecyclerView;
    HomeAdapter homeAdapter;
    RecyclerView.LayoutManager homeLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        membuat instance dari tiap objek class
        sessionManager = new SessionManager(this);
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        lgout = findViewById(R.id.lgout);
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });
        tabung = (ImageButton) findViewById(R.id.tabung);
        tabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTabungActivity();
            }
        });

        tarik = (ImageButton) findViewById(R.id.tarik);
        tarik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTarikngActivity();
            }
        });


        //        memanggil fungsi refresh data recycler
        loadSaldoData();
    }

    public void OpenTabungActivity() {
        Intent intent = new Intent(this, TabungActivity.class);
        startActivity(intent);
    }

    public void OpenTarikngActivity() {
        Intent intent = new Intent(this, TarikActivity.class);
        startActivity(intent);
    }

//    fungsi untuk merefresh dan mendapatkan data untuk recyclerview home
    private void loadSaldoData(){
        progressDialog.setMessage("Memperbarui informasi...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest saldoRequest = new StringRequest(Request.Method.GET, ApiUrl.HOME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try{
                    ArrayList<HomeItem> saldoList = new ArrayList<>();

//                    mengambil array terluar response
                    JSONArray responseArray = new JSONArray(response);

//                    mengambil tiap objek dalam array
                    for (int i = 0; i < responseArray.length(); i++){
                        JSONObject objectSaldo = responseArray.getJSONObject(i);

                        String id_anggota = objectSaldo.getString("id_anggota");
                        String nama = objectSaldo.getString("nama");
                        String total_setoran = objectSaldo.getString("total_setoran");
                        String total_penarikan = objectSaldo.getString("total_penarikan");
                        String total_saldo = objectSaldo.getString("total_saldo");

                        saldoList.add( new HomeItem(id_anggota, nama, total_setoran, total_penarikan, total_saldo));
                    }

                    homeRecyclerView = findViewById(R.id.rvHome);
                    homeRecyclerView.setHasFixedSize(true);
                    homeLayoutManager = new LinearLayoutManager(getApplicationContext());
                    homeAdapter = new HomeAdapter(saldoList);

                    homeRecyclerView.setLayoutManager(homeLayoutManager);
                    homeRecyclerView.setAdapter(homeAdapter);
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

        requestQueue.add(saldoRequest);
    }
}
