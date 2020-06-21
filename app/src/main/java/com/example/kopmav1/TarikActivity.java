package com.example.kopmav1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TarikActivity extends AppCompatActivity {
    Button btnInsert, btnKembali;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik);

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnKembali = (Button) findViewById(R.id.btn_kembali);
        pd = new ProgressDialog(TarikActivity.this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarikActivity.this,TamrikActivity.class);
                startActivity(intent);
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(TarikActivity.this,HomeActivity.class);
                startActivity(hapus);
            }
        });
    }
}
