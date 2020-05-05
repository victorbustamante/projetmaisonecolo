package com.example.ecohome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


import com.example.ecohome.utils.Tools;

import java.util.ArrayList;

public class LightActivity extends AppCompatActivity {

    TextView nom;
    TextView prenom;
    String email = "ugh";
    Button onoff;

    final AppCompatActivity src = this;

    private Button switchactivity;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        onoff = findViewById(R.id.onoff);

        Intent it = getIntent();
        Log.d("user", "LightActivity passage intent ok");
        final ArrayList<String> client = it.getStringArrayListExtra("client");
        Log.d("user", "LightActivity passage client ok: " +client.get(0) +" / " +client.get(1));
        int length = getIntent().getIntExtra("length",0);
        Log.d("user", "LightActivity passage taille client ok : "+length);


        nom.setText(client.get(0));
        prenom.setText(client.get(1));


        onoff.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })

        );

        switchactivity = findViewById(R.id.switchactivity);


    }

}