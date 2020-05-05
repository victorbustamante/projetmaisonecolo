package com.example.ecohome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecohome.utils.RequestHandler;
import com.example.ecohome.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText nomtext;
    private EditText prenomtext;
    private EditText emailtext;
    private EditText mdp1;
    private EditText mdp2;
    private EditText passwordtext;
    private Button sendButton;
    private Button coButton;
    private String TAG = "registeractivity";

    final AppCompatActivity src = this;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomtext = (EditText)findViewById(R.id.nomText);
        prenomtext = (EditText)findViewById(R.id.prenomText);
        emailtext = (EditText)findViewById(R.id.emailText);
        mdp1 = (EditText)findViewById(R.id.mdp1);
        mdp2 = (EditText)findViewById(R.id.mdp2);
        sendButton = (Button) findViewById(R.id.sendbutton);
        coButton = (Button) findViewById(R.id.cobutton);

        //final EditText[] editText = {nomText,prenomText,emailText,mdp1,mdp2};
        //final ArrayList<String> client = new ArrayList<String>();



        coButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.intentActivity(src, MainActivity.class);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendButton.setEnabled(false);
                Verifusers();
            }
        });

        /*sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tools.verifyText(editText)){
                    if (Tools.sameText(editText, 3)){
                        Log.d("registered",editText[0].getText().toString());
                        Log.d("saved password",editText[0].getText().toString());
                        for (int x = 0 ; x<editText.length ; x++){
                            client.add(editText[x].getText().toString());
                        }

                        Tools.intentActivity(src, ViewActivity.class, client);
                    }
                }

            }
        });*/
    }
    private void Verifusers() {
        final String lastname = nomtext.getText().toString().trim();
        final String name = prenomtext.getText().toString().trim();
        final String email = emailtext.getText().toString().trim();
        String password = null;

        if (Tools.sameText(mdp1,mdp2))
            password=mdp1.getText().toString().trim();


        //validations
        if (TextUtils.isEmpty(lastname)) {
            nomtext.setError("Please enter lastname");
            nomtext.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            prenomtext.setError("Enter a name");
            prenomtext.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            emailtext.setError("Enter a email");
            emailtext.requestFocus();
            return;
        }if (TextUtils.isEmpty(password)) {
            mdp1.setError("Enter a password");
            mdp1.requestFocus();
            return;
        }
        //if it passes all the validations
        //executing the async task
        RegisterActivity.RegisterUser rs = new RegisterActivity.RegisterUser(lastname,name,email,password);
        rs.execute();

    }

    private class RegisterUser extends AsyncTask<Void, Void, String> {
        String URL_LOGIN = "http://192.168.1.26/ecoapi/api/register.php";
        //private ProgressBar progressBar;
        private String lastname,name,email,password;
        RegisterUser(String lastname,String name,String email, String password){
            this.lastname = lastname;
            this.name = name;
            this.email = email;
            this.password = password;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar = findViewById(R.id.progressBar);
            //progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: ");
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("nom", lastname);
            params.put("prenom", name);
            params.put("pseudo", email);
            params.put("password", password);
            //returing the response
            return requestHandler.sendPostRequest(URL_LOGIN, params);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SignUp"," : "+s);

            //hiding the progressbar after completion
            //progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject  = new JSONObject(s);

                //converting response to json object
                //if no error in response
                if (jsonObject.getBoolean("error"))
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                if (!jsonObject.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    //JSONObject userJson = obj.getJSONObject("users");
                    JSONObject userJson = jsonObject.getJSONObject("user");
                    Log.d(TAG, "\n" +userJson.toString());


                    //creating a new user object
                    /*User user = new User(
                            jsonObject.getString("username"),
                            jsonObject.getString("password")
                    );*/
                    //storing the user in shared preferences
                    //PrefManager.getInstance(getApplicationContext()).setUserLogin(user);
                    //starting the profile activity
                    String myps =  userJson.getString("email");
                    Toast.makeText(getApplicationContext(), "bienvenue ! " +userJson.getString("nom"), Toast.LENGTH_SHORT).show();
                    myps = myps.trim();
                    Log.d(TAG,myps);
                    if (email.equals(myps)) {
                        finish();
                        Tools.intentActivity(src, SensorsTableActivity.class);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur pseudo ou mot de passe", Toast.LENGTH_SHORT).show();
                    sendButton.setEnabled(true);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
