package com.example.kopmav1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton tabung;
    private ImageButton tarik;
    Button lgout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lgout = (Button) findViewById(R.id.lgout);
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLoginActivity();
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
    }

    public void OpenTabungActivity(){
        Intent intent = new Intent(this, TabungActivity.class);
        startActivity(intent);
    }
    public void OpenTarikngActivity(){
        Intent intent = new Intent(this, TarikActivity.class);
        startActivity(intent);
    }
    public void OpenLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
