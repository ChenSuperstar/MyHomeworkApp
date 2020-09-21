package com.example.myhomeworkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView score;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button resetBtn;
    int nowScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = (TextView)findViewById(R.id.score);
        btn1 = (Button)findViewById((R.id.btn1));
        btn2 = (Button)findViewById((R.id.btn2));
        btn3 = (Button)findViewById((R.id.btn3));
        resetBtn = (Button)findViewById((R.id.resetBtn));

        nowScore = Integer.parseInt(score.getText().toString());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScore = nowScore+1;
                score.setText(String.valueOf(nowScore));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScore = nowScore+2;
                score.setText(String.valueOf(nowScore));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScore = nowScore+3;
                score.setText(String.valueOf(nowScore));
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScore = 0;
                score.setText(String.valueOf(nowScore));
            }
        });


    }
}