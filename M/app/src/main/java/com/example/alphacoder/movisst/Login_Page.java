package com.example.alphacoder.movisst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alphacoder.movisst.Model.AccessToken;
import com.example.alphacoder.movisst.Model.LoginResult;
import com.example.alphacoder.movisst.Network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Page extends AppCompatActivity {

    Button submit;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Login successful
                Call<AccessToken> newAccessToken = ApiClient.getInterface().getAccessToken(getResources().getString(R.string.api_key));

                newAccessToken.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (response.isSuccessful())
                        {
                            AccessToken accesstoken=response.body();

                            String token=accesstoken.getRequest_token();

                            save_token(token);

                            Call<LoginResult> newresult= ApiClient.getInterface().tryLogin(username.getText().toString(),password.getText().toString(),getResources().getString(R.string.api_key),token);

                            newresult.enqueue(
                                    new Callback<LoginResult>() {
                                        @Override
                                        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(Login_Page.this, "Nailed it", Toast.LENGTH_SHORT).show();

                                                Intent i=new Intent();
                                                i.setClass(getApplicationContext(),Home_Page.class);
                                                startActivity(i);

                                            } else {
                                                Toast.makeText(Login_Page.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<LoginResult> call, Throwable t) {
                                            Toast.makeText(Login_Page.this, "I am Deadpool", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            );
                        }
                        else
                        {
                            Toast.makeText(Login_Page.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {

                        Toast.makeText(Login_Page.this, "I am Deadpool", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
    public void save_token(String token)
    {
        SharedPreferences sp =
                getSharedPreferences("Movisst",
                        Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token",token);
        editor.commit();
    }
}
