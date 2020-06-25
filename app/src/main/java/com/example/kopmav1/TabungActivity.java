package com.example.kopmav1;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TabungActivity extends AppCompatActivity {

    Button btnInsert, btnKembali;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabung);

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnKembali = (Button) findViewById(R.id.btn_kembali);
        pd = new ProgressDialog(TabungActivity.this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabungActivity.this,TambungActivty.class);
                startActivity(intent);
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(TabungActivity.this,HomeActivity.class);
                startActivity(hapus);
            }
        });
    }
}
