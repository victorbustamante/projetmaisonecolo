package com.example.ecohome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecohome.utils.Tools;

public class SensorsTableActivity extends AppCompatActivity {


    public Button windButton;
    public Button uvButton;
    public Button tempButton;
    public Button rainButton;
    public Button elecButton;
    public Button lightButton;

    final AppCompatActivity src = this;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorstable);

        windButton = findViewById(R.id.button0);
        uvButton = findViewById(R.id.button1);
        tempButton = findViewById(R.id.button2);
        rainButton = findViewById(R.id.button3);
        elecButton = findViewById(R.id.button4);
        lightButton = findViewById(R.id.button5);

        windButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.intentActivity(src,SensorsActivity.class,"DISPLAY",4);
            }
        });

        uvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        elecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
