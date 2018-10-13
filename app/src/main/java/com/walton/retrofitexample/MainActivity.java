package com.walton.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.etusername);
        passwordEditText = findViewById(R.id.etpassword);
       loginButton = findViewById(R.id.btnlogin);
        progressBar = findViewById(R.id.progress_bar);

       loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnlogin:

                try {
                    JSONObject login = new JSONObject();
                    login.put("username", usernameEditText.getText().toString().trim());
                    login.put("password",passwordEditText.getText().toString().trim());





                progressBar.setVisibility(View.VISIBLE);
                MainApplication.apiManager.getUser(login, new Callback<User>() {


                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        progressBar.setVisibility(View.GONE);
                        User responseUser = response.body();
                        if (response.isSuccessful() && responseUser != null) {
                            Toast.makeText(MainActivity.this,
                                    String.format("Username %s and token type %s Access token %s  ",
                                            responseUser.getUsername(),
                                            responseUser.getTokentype(),
                                            responseUser.getAcceesstoken()),
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    String.format("Response is %s", String.valueOf(response.code()))
                                    , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,
                                "Error is " + t.getMessage()
                                , Toast.LENGTH_LONG).show();
                    }
                });


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}