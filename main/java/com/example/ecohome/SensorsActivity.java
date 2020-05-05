package com.example.ecohome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class SensorsActivity extends AppCompatActivity {

    private String TAG = "sensorsactivity";
    private TextView id;
    private TextView datetime;
    private TextView value1type;
    private TextView bungalowid;
    private String value = "no value";

    public interface MyCallback {
        // Declaration of the template function for the interface
        public void updateMyText(String myString);
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        id = (TextView) findViewById(R.id.idview);
        datetime = (TextView) findViewById(R.id.datetimeview);
        bungalowid = (TextView) findViewById(R.id.bungalowidview);


        public void updateMyText(String value) {
            (value1type.setText(value);
        }
        value1type = (TextView) findViewById(R.id.value1);




        Bundle bundle = getIntent().getExtras();

        Log.d(TAG, "onCreate: " +bundle.getInt("DISPLAY"));

        //on recoit le GetExtra dans un switch
        switch (bundle.getInt("DISPLAY")){
            case 4 :
                @Override
                public void updateMyText(String value) {
                (value1type.setText(value);
            }

                Log.d(TAG, "onCreate: view on capteur vent" );
                break;

            case 2 :

                break;

        }

    }

}
