package com.example.myhomeworkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //声明控件对象
    EditText input;
    TextView output;
    String cd;
    String fd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener((View.OnClickListener) this);

        cd = input.getText().toString();
        double temp = (Double.parseDouble(cd) - 32)/1.8;
        fd = String.valueOf(temp);

        output.setText(fd);

    }
}