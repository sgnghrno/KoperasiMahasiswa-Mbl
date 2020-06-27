package com.example.kopmav1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kopmav1.penarikan.TarikActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TamrikActivity extends AppCompatActivity {
    private ProgressDialog pd;
    Button btnsimpan, btnkeluar;
    EditText tarik;

    Spinner id;
    String id_anggota;

    private RequestQueue queue;

    ArrayList<String> spinnerItem = new ArrayList<String>();
    ArrayAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamrik);queue = Volley.newRequestQueue(this);

        id = findViewById(R.id.spinner_tarik);
        tarik = (EditText) findViewById(R.id.penarikan);
        btnkeluar = (Button) findViewById(R.id.keluar);
        btnsimpan = (Button) findViewById(R.id.tarik);
        pd = new ProgressDialog(TamrikActivity.this);

        loadSpinner();

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerText = id.getSelectedItem().toString().trim();
                int indexOf = spinnerText.indexOf("-");

//                mengambil teks sebelum -
                if(indexOf != -1){
                    id_anggota = spinnerText.substring(0, indexOf);
                }

                simpanData();
            }
        });

        btnkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(TamrikActivity.this, TarikActivity.class);
                startActivity(main);
            }
        });
    }

    private void loadSpinner() {
        pd.setMessage("Memperbarui informasi...");
        pd.setCancelable(false);
        pd.show();

        StringRequest spinnerRequest = new StringRequest(Request.Method.GET, ApiUrl.ANGGOTA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                try {
                    JSONArray arrayResponse = new JSONArray(response);

                    for (int i = 0; i < arrayResponse.length(); i++){
                        JSONObject object = arrayResponse.getJSONObject(i);

                        String id_anggota = object.getString("id_anggota");
                        String nama = object.getString("nama");

                        spinnerItem.add(id_anggota+"-"+nama);
                    }

                    spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItem);
                    id.setAdapter(spinnerAdapter);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(spinnerRequest);
    }

    private void simpanData()
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ApiUrl.Api_Insert1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(TamrikActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(TamrikActivity.this,TarikActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(TamrikActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_anggota", id_anggota);
                map.put("penarikan",tarik.getText().toString());
                return map;
            }
        };

        queue.add(sendData);
    }
}
