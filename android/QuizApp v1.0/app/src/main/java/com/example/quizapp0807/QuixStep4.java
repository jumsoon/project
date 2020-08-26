package com.example.quizapp0807;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuixStep4 extends AppCompatActivity {

    Button quiz4_o, quiz4_x, quiz4next, quiz4home;
    TextView O, X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quix_step4);

        quiz4_o = findViewById(R.id.quiz4_o);
        quiz4_x = findViewById(R.id.quiz4_x);
        quiz4next = findViewById(R.id.quiz4next);
        quiz4home = findViewById(R.id.quiz4home);
        O = findViewById(R.id.jung5);
        X = findViewById(R.id.ooo5);

        quiz4next.setEnabled(false);

        quiz4_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz4_o.isClickable() == true) {
                    O.setVisibility(View.INVISIBLE);
                    X.setVisibility(View.VISIBLE);
                }
                else {
                    O.setVisibility(View.VISIBLE);
                    X.setVisibility(View.INVISIBLE);
                }
            }
        });

        quiz4_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz4_x.isClickable() == true) {
                    X.setVisibility(View.INVISIBLE);
                    O.setVisibility(View.VISIBLE);
                }
                else {
                    X.setVisibility(View.VISIBLE);
                    O.setVisibility(View.INVISIBLE);
                }
                quiz4next.setEnabled(true);

            }
        });

        quiz4next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizStep5.class);
                startActivity(intent);
                finish();
            }
        });

        quiz4home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }
}