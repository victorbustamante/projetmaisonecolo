package com.example.ecohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ecohome.utils.RequestHandler;
import com.example.ecohome.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText passwordtext;
    private Button cobutton;
    private Button regbutton;
    private String TAG = "mainactivity";
    final AppCompatActivity src = this;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                email = (EditText) findViewById(R.id.emailText);
                passwordtext = (EditText) findViewById(R.id.passwordText);
                cobutton = (Button) findViewById(R.id.coButton);
                regbutton = (Button) findViewById(R.id.regButton);
                //final HttpClient httpClient = new HttpClient("http://192.168.1.45/api-ecohome");

                final EditText[] editTexts = {email,passwordtext};

        /*cobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tools.verifyText(editTexts)) {
                        httpClient.fetch(new HttpClient.Listeners() {
                            @Override
                            public void onPostExecute(String result) {
                                Log.d("HTTP", "onPostExecute: " + result);
                            }
                        }, "/api/user/get.php?email=" + email);
                        //if ()
                        //    Tools.intentActivity(src, ViewActivity.class);
                }

            }
        });*/
                cobutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Verifusers();
                    }
                });

                regbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tools.intentActivity(src, RegisterActivity.class);
                    }
                });



            }
            private void Verifusers() {
                final String username = email.getText().toString().trim();
                final String password = passwordtext.getText().toString().trim();

                //validations
                if (TextUtils.isEmpty(username)) {
                    email.setError("Please enter username");
                    email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordtext.setError("Enter a password");
                    passwordtext.requestFocus();
                    return;
                }
                //if it passes all the validations
                //executing the async task
                LoginUser rs = new LoginUser(username,password);
                rs.execute();

            }

            private class LoginUser extends AsyncTask<Void, Void, String> {
                String URL_LOGIN = "http://192.168.1.26/ecoapi/api/login.php";
                //private ProgressBar progressBar;
                private String username,password;
                LoginUser(String username, String password){
                    this.username = username;
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
            params.put("pseudo", username);
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
                if (!jsonObject.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    //JSONObject userJson = obj.getJSONObject("users");
                    JSONArray jsonArray = jsonObject.getJSONArray("user");
                    Log.d(TAG, "\n" +jsonArray.toString());

                    JSONObject userJson = jsonArray.getJSONObject(0);

                    //creating a new user object
                    /*User user = new User(
                            jsonObject.getString("username"),
                            jsonObject.getString("password")
                    );*/
                    //storing the user in shared preferences
                    //PrefManager.getInstance(getApplicationContext()).setUserLogin(user);
                    //starting the profile activity
                    String myps =  userJson.getString("email");
                    String ps =  userJson.getString("mdp");
                    Toast.makeText(getApplicationContext(), "bienvenue ! " +userJson.getString("nom"), Toast.LENGTH_SHORT).show();
                    myps = myps.trim();
                    Log.d(TAG,myps);
                    Log.d(TAG,ps);
                    if (username.equals(myps)) {
                        Tools.intentActivity(src, SensorsTableActivity.class);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur pseudo ou mot de passe", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
