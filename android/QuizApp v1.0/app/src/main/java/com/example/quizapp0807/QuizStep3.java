package com.example.quizapp0807;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizStep3 extends AppCompatActivity {

    Button quiz2_o3, quiz2_x3, quiz2next3, quiz2home3;
    TextView O, X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_step3);

        quiz2_o3 = findViewById(R.id.quiz2_o3);
        quiz2_x3 = findViewById(R.id.quiz2_x3);
        quiz2next3 = findViewById(R.id.quiz2next3);
        quiz2home3 = findViewById(R.id.quiz2home3);
        O = findViewById(R.id.jung5);
        X = findViewById(R.id.ooo5);

        quiz2next3.setEnabled(false);

        quiz2_o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz2_o3.isClickable() == true) {
                    O.setVisibility(View.VISIBLE);
                    X.setVisibility(View.INVISIBLE);
                }
                else {
                    O.setVisibility(View.INVISIBLE);
                    X.setVisibility(View.VISIBLE);
                }
                quiz2next3.setEnabled(true);
            }
        });

        quiz2_x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quiz2_x3.isClickable() == true) {
                    X.setVisibility(View.VISIBLE);
                    O.setVisibility(View.INVISIBLE);
                }
                else {
                    X.setVisibility(View.INVISIBLE);
                    O.setVisibility(View.VISIBLE);
                }
            }
        });

        quiz2next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuixStep4.class);
                startActivity(intent);
                finish();
            }
        });

        quiz2home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}