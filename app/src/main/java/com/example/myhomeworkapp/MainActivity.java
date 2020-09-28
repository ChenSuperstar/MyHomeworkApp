package com.example.myhomeworkapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "111";
    private EditText input;
    private Button R2DBtn;
    private Button R2EBtn;
    private Button R2WBtn;
    private Button configBtn;
    private TextView output;
    private double rmbAcc;
    private double R2DER = 0.1472;
    private double R2EER = 0.1251;
    private double R2WER = 171.4626;
    private double result;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
        R2DBtn = (Button)findViewById(R.id.R2DBtn);
        R2EBtn = (Button)findViewById(R.id.R2EBtn);
        R2WBtn = (Button)findViewById(R.id.R2WBtn);
        configBtn = (Button)findViewById(R.id.config_btn);

        //重置汇率


        R2DBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                    Toast.makeText(MainActivity.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Double.parseDouble(input.getText().toString());
                    result = rmbAcc * R2DER;
                    output.setText(String.valueOf(result));
                }
            }
        });

        R2EBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                   Toast.makeText(MainActivity.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Double.parseDouble(input.getText().toString());
                    result = rmbAcc * R2EER;
                    output.setText(String.valueOf(result));
                }
            }
        });

        R2WBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                    Toast.makeText(MainActivity.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Double.parseDouble(input.getText().toString());
                    result = rmbAcc * R2WER;
                    output.setText(String.valueOf(result));
                }
            }
        });

        configBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent config = new Intent(MainActivity.this, MainActivity2.class);
               //这里的是什么
                config.putExtra("R2Drate",R2DER);
                config.putExtra("R2Erate",R2EER);
                config.putExtra("R2Wrate",R2WER);
                startActivityForResult(config,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                R2DER = intent.getDoubleExtra("R2Drate",R2DER);
                R2EER = intent.getDoubleExtra("R2Erate",R2EER);
                R2WER = intent.getDoubleExtra("R2Wrate",R2WER);

                Log.i(TAG, "now RMB to Dollar Exchange Rate is :" + R2DER );
                Log.i(TAG, "now RMB to EUR Exchange Rate is :" + R2EER );
                Log.i(TAG, "now RMB to WON Exchange Rate is :" + R2WER );

            }
        }
        super.onActivityResult(requestCode,resultCode,intent);

    }
}