package com.example.quizapp0807;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizStep2 extends AppCompatActivity {

    Button quiz2_o, quiz2_x, quiz2next, quiz2home;
    TextView O, X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_step2);


        quiz2_o = findViewById(R.id.quiz2_o);
        quiz2_x = findViewById(R.id.quiz2_x);
        quiz2next = findViewById(R.id.quiz2next);
        quiz2home = findViewById(R.id.quiz2home);
        O = findViewById(R.id.jung2);
        X = findViewById(R.id.ooo2);

        quiz2next.setEnabled(false);

        quiz2_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz2_o.isClickable() == true) {
                    O.setVisibility(View.INVISIBLE);
                    X.setVisibility(View.VISIBLE);
                }
                else {
                    O.setVisibility(View.VISIBLE);
                    X.setVisibility(View.INVISIBLE);
                }
            }
        });

        quiz2_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz2_x.isClickable() == true) {
                    X.setVisibility(View.INVISIBLE);
                    O.setVisibility(View.VISIBLE);
                }
                else {
                    X.setVisibility(View.VISIBLE);
                    O.setVisibility(View.INVISIBLE);
                }
                quiz2next.setEnabled(true);

            }
        });

        quiz2next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizStep3.class);
                startActivity(intent);
                finish();
            }
        });

        quiz2home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }
}