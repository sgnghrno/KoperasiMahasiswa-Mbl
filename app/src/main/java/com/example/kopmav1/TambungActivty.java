package com.example.kopmav1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class TambungActivty extends AppCompatActivity {
    private ProgressDialog progress;
    Button simpan, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambung_activty);

        keluar = (Button) findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(TambungActivty.this,TabungActivity.class);
                startActivity(hapus);
            }
        });
    }
}