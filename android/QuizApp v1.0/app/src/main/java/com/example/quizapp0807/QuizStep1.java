package com.example.quizapp0807;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizStep1 extends AppCompatActivity {

    Button quiz1_o, quiz1_x, quiz1next, quiz1home;
    TextView O, X;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_step1);

        quiz1_o = findViewById(R.id.quiz2_o);
        quiz1_x = findViewById(R.id.quiz2_x);
        quiz1next = findViewById(R.id.quiz2next);
        quiz1home = findViewById(R.id.quiz2home);
        O = findViewById(R.id.jung);
        X = findViewById(R.id.ooo);

        quiz1next.setEnabled(false);

        quiz1_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz1_o.isClickable() == true) {
                    O.setVisibility(View.VISIBLE);
                    X.setVisibility(View.INVISIBLE);
                }
                else {
                    O.setVisibility(View.INVISIBLE);
                    X.setVisibility(View.VISIBLE);
                }
                quiz1next.setEnabled(true);
            }
        });

        quiz1_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz1_x.isClickable() == true) {
                    X.setVisibility(View.VISIBLE);
                    O.setVisibility(View.INVISIBLE);
                }
                else {
                    X.setVisibility(View.INVISIBLE);
                    O.setVisibility(View.VISIBLE);
                }
            }
        });

        quiz1next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizStep2.class);
                startActivity(intent);
                finish();

            }
        });

        quiz1home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }
}