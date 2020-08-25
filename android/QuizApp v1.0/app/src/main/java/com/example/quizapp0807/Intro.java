package com.example.quizapp0807;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Intro extends AppCompatActivity {

    private Handler handler;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Intro.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getSupportActionBar().hide();

        handler = new Handler();
        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnable);
    }
}