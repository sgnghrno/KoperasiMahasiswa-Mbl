package com.example.kopmav1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kopmav1.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;

//    untuk cek apakah sudah login
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        if (sessionManager.isLogin()){
            Intent keHome = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(keHome);
            finish();
        } else {
            img = findViewById(R.id.img);
            img.animate().alpha(4000).setDuration(0);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent dsp = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(dsp);
                    finish();
                }
            },4000);
        }
    }
}
