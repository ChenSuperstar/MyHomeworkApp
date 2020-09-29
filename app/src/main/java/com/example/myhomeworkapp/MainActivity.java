package com.example.myhomeworkapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {

    private SharedPreferences sharedPreferences;

    private static final String TAG = "MainActivity";
    private EditText input;
    private Button R2DBtn;
    private Button R2EBtn;
    private Button R2WBtn;
    private Button configBtn;
    private TextView output;
    private  Button networkBtn;
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
        networkBtn = (Button)findViewById(R.id.network);




        R2DBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(input.getText().toString())){

                    Toast.makeText(MainActivity.this, "Please input some number", Toast.LENGTH_SHORT).show();

                }else {
                    rmbAcc = Float.parseFloat(input.getText().toString());

//                    R2DER = sharedPreferences.getFloat("dollar_rate",R2DER);
                    result = rmbAcc * (100/R2DER);
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
                    rmbAcc = Float.parseFloat(input.getText().toString());

//                    R2EER = sharedPreferences.getFloat("euro_rate", R2EER);
                    result = rmbAcc * (100/R2EER);
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
                    rmbAcc = Float.parseFloat(input.getText().toString());

//                    R2WER = sharedPreferences.getFloat("won_rate", R2WER);
                    result = rmbAcc * (100/R2WER);
                    output.setText(String.valueOf(result));
                }
            }
        });

        configBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent config = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(config);
            }
        });

        networkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler=new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if(msg.what==5){
//                            String str=(String)msg.obj;
                            HashMap<String, Float> rate = (HashMap<String, Float>)msg.obj;
                            Log.i(TAG," handleMessage:getMessage msg="+"美元"+rate.get("美元"));
                            Log.i(TAG," handleMessage:getMessage msg="+"欧元"+rate.get("欧元"));
                            Log.i(TAG," handleMessage:getMessage msg="+"韩元"+rate.get("韩元"));

                            R2DER = rate.get("美元");
                            R2EER = rate.get("欧元");
                            R2WER = rate.get("韩元");


                        }

                        super.handleMessage(msg);
                    }
                };
                Thread t=new Thread(new MyThread(handler));
                t.start();
            }
        });

    }

}