package com.example.myhomeworkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView AScore;
    private Button ABtn1;
    private Button ABtn2;
    private Button ABtn3;

    private TextView BScore;
    private Button BBtn1;
    private Button BBtn2;
    private Button BBtn3;

    private Button resetBtn;
    int nowScoreA;
    int nowScoreB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AScore = (TextView)findViewById(R.id.AScore);
        ABtn1 = (Button)findViewById((R.id.ABtn1));
        ABtn2 = (Button)findViewById((R.id.ABtn2));
        ABtn3 = (Button)findViewById((R.id.ABtn3));

        BScore = (TextView)findViewById(R.id.BScore);
        BBtn1 = (Button)findViewById((R.id.BBtn1));
        BBtn2 = (Button)findViewById((R.id.BBtn2));
        BBtn3 = (Button)findViewById((R.id.BBtn3));

        resetBtn = (Button)findViewById((R.id.resetBtn));

        nowScoreA = Integer.parseInt(AScore.getText().toString());
        nowScoreB = Integer.parseInt(BScore.getText().toString());

        ABtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreA = nowScoreA+1;
                AScore.setText(String.valueOf(nowScoreA));
            }
        });

        ABtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreA = nowScoreA+2;
                AScore.setText(String.valueOf(nowScoreA));
            }
        });

        ABtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreA = nowScoreA+3;
                AScore.setText(String.valueOf(nowScoreA));
            }
        });

        BBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreB = nowScoreB + 1 ;
                BScore.setText(String.valueOf(nowScoreB));
            }
        });

        BBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreB = nowScoreB + 2 ;
                BScore.setText(String.valueOf(nowScoreB));
            }
        });

        BBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreB = nowScoreB + 3 ;
                BScore.setText(String.valueOf(nowScoreB));
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowScoreA = 0;
                nowScoreB = 0;
                AScore.setText(String.valueOf(nowScoreA));
                BScore.setText(String.valueOf(nowScoreB));
            }
        });


    }
}