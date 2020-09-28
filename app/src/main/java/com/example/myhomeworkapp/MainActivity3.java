package com.example.myhomeworkapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private static final String TAG = "111";
    private EditText input;
    private Button R2DBtn;
    private Button R2EBtn;
    private Button R2WBtn;
    private Button configBtn;
    private TextView output;
    private float rmbAcc;
    private float R2DER = 0.14f;
    private float R2EER = 0.12f;
    private float R2WER = 171.46f;
    private float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("rate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("dollar_rate", 0.1f);
        editor.putFloat("euro_rate", 0.1f);
        editor.putFloat("won_rate",0.2f);
        editor.apply();

        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
        R2DBtn = (Button)findViewById(R.id.R2DBtn);
        R2EBtn = (Button)findViewById(R.id.R2EBtn);
        R2WBtn = (Button)findViewById(R.id.R2WBtn);
        configBtn = (Button)findViewById(R.id.config_btn);




        R2DBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                    Toast.makeText(MainActivity3.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Float.parseFloat(input.getText().toString());

                    R2DER = sharedPreferences.getFloat("dollar_rate",R2DER);
                    result = rmbAcc * R2DER;
                    output.setText(String.valueOf(result));
                }
            }
        });

        R2EBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                    Toast.makeText(MainActivity3.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Float.parseFloat(input.getText().toString());

                    R2EER = sharedPreferences.getFloat("euro_rate", R2EER);
                    result = rmbAcc * R2EER;
                    output.setText(String.valueOf(result));
                }
            }
        });

        R2WBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                    Toast.makeText(MainActivity3.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Float.parseFloat(input.getText().toString());

                    R2WER = sharedPreferences.getFloat("won_rate", R2WER);
                    result = rmbAcc * R2WER;
                    output.setText(String.valueOf(result));
                }
            }
        });

        configBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent config = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(config);
            }
        });

    }

}