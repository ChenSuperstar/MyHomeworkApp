package com.example.myhomeworkapp;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private EditText R2D;
    private EditText R2E;
    private EditText R2W;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        double dollarRate = intent.getDoubleExtra("R2Drate",0.0f);
        double euroRate = intent.getDoubleExtra("R2Erate",0.0f);
        double wonRate = intent.getDoubleExtra("R2Wrate",0.0f);
        R2D = (EditText)findViewById(R.id.R2DErate);
        R2E = (EditText)findViewById(R.id.R2EErate);
        R2W = (EditText)findViewById(R.id.R2WErate);
        R2D.setHint(dollarRate+"");
        R2E.setHint(euroRate+"");
        R2W.setHint(wonRate+"");

        saveBtn = (Button)findViewById(R.id.saveBtn);




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent main = new Intent(MainActivity2.this, MainActivity.class);
                    R2D = findViewById(R.id.R2DErate);
                    R2E = findViewById(R.id.R2EErate);
                    R2W = findViewById(R.id.R2WErate);

                    double rate1 = Double.parseDouble(R2D.getText().toString());
                    double rate2 = Double.parseDouble(R2E.getText().toString());
                    double rate3 = Double.parseDouble(R2W.getText().toString());


                    main.putExtra("R2Drate", rate1);
                    main.putExtra("R2Erate", rate2);
                    main.putExtra("R2Wrate", rate3);

                    //可以使用Bundle打包传输数据


                    Log.i(TAG, "original RMB to Dollar Exchange Rate is :" + R2D );
                    Log.i(TAG, "original RMB to EUR Exchange Rate is :" + R2E );
                    Log.i(TAG, "original RMB to WON Exchange Rate is :" + R2W );
                    setResult(RESULT_OK, main);
                    finish();
                }

        });
    }
}