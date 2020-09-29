package com.example.myhomeworkapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String TAG = "MainActivity2";
    private EditText R2D;
    private EditText R2E;
    private EditText R2W;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sharedPreferences = getSharedPreferences("rate", Activity.MODE_PRIVATE);
        final float dollarRate = sharedPreferences.getFloat("dollar_rate", 0.0f);
        final float euroRate = sharedPreferences.getFloat("euro_rate", 0.0f);
        final float wonRate = sharedPreferences.getFloat("won_rate", 0.0f);
        R2D = (EditText)findViewById(R.id.R2DErate);
        R2E = (EditText)findViewById(R.id.R2EErate);
        R2W = (EditText)findViewById(R.id.R2WErate);
        R2D.setText(dollarRate+"");
        R2E.setText(euroRate+"");
        R2W.setText(wonRate+"");

        saveBtn = (Button)findViewById(R.id.saveBtn);




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(MainActivity2.this, MainActivity.class);
                R2D = findViewById(R.id.R2DErate);
                R2E = findViewById(R.id.R2EErate);
                R2W = findViewById(R.id.R2WErate);

                float rate1 = Float.parseFloat(R2D.getText().toString());
                float rate2 = Float.parseFloat(R2E.getText().toString());
                float rate3 = Float.parseFloat(R2W.getText().toString());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("dollar_rate", rate1);
                editor.putFloat("euro_rate", rate2);
                editor.putFloat("won_rate", rate3);
                editor.apply();

                Log.i(TAG, "original RMB to Dollar Exchange Rate is :" + R2D );
                Log.i(TAG, "original RMB to EUR Exchange Rate is :" + R2E );
                Log.i(TAG, "original RMB to WON Exchange Rate is :" + R2W );

                finish();
            }

        });
    }
}