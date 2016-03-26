package com.example.alphacoder.movisst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alphacoder.movisst.Model.AccessToken;
import com.example.alphacoder.movisst.Network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Page extends AppCompatActivity {

    Button submit;
    final static String api_key="de3e6b37e4f6eb8ff97b6acb87b35264";
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
                Call<AccessToken> newAccessToken = ApiClient.getInterface().getAccessToken();

                newAccessToken.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (response.isSuccessful())
                        {
                            Toast.makeText(Login_Page.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            //AccessToken token=new AccessToken();
                            //token=response.body();
                            Log.i("login", String.valueOf(response.code()));
                            //Toast.makeText(Login_Page.this, token.getStatus_message(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {

                        Toast.makeText(Login_Page.this, "Fuckedup", Toast.LENGTH_SHORT).show();
                    }
                });
                //Intent i=new Intent();
                //i.setClass(getApplicationContext(),Home_Page.class);
                //startActivity(i);

                //else
            }
        });

    }
}
