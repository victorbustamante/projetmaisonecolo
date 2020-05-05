package com.example.ecohome.utils;

import android.content.Intent;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Tools {

    public static Intent intentActivity(AppCompatActivity src, Class<?> cls,String name, Integer nb){
        Intent activity = new Intent(src.getApplicationContext(),cls);
        activity.putExtra(name,nb);
        src.startActivity(activity);
        //src.finish();
        return activity;
    }
    public static Intent intentActivity(AppCompatActivity src, Class<?> cls){
        Intent activity = new Intent(src.getApplicationContext(),cls);
        src.startActivity(activity);
        //src.finish();
        return activity;
    }

    public static boolean sameText (EditText a, EditText b){
        if(!a.getText().toString().equals(b.getText().toString())){
            a.setError("mot de passe pas identique");
            return false;
        }
        return true;
    }
    public static boolean verifyText(EditText[] editTexts){
        int errors = 0;
        for (int i = 0 ; i < editTexts.length ; i++ ) {
            if (editTexts[i].getText().toString().isEmpty()) {
                editTexts[i].setError("remplire le champs");
                errors ++;
            }
        }
        return (errors == 0);
    }
}
