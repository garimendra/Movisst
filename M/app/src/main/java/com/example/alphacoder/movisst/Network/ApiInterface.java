package com.example.alphacoder.movisst.Network;


import com.example.alphacoder.movisst.Model.AccessToken;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by alphacoder on 26/03/16.
 */
public interface ApiInterface {
    @GET("/authentication/token/new")
    Call<AccessToken> getAccessToken();
}
