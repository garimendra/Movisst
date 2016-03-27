package com.example.alphacoder.movisst.Network;


import com.example.alphacoder.movisst.Model.AccessToken;
import com.example.alphacoder.movisst.Model.LoginResult;
import com.example.alphacoder.movisst.Model.Movie;
import com.example.alphacoder.movisst.Model.Movie_result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by alphacoder on 26/03/16.
 */
public interface ApiInterface {
    @GET("authentication/token/new")
    Call<AccessToken> getAccessToken(@Query("api_key")String api_key);

    @GET("authentication/token/validate_with_login")
    Call<LoginResult> tryLogin(@Query("username")String username,
                    @Query("password")String password,
                    @Query("api_key")String api_key,
                    @Query("request_token")String request_token
                    );
    @GET("movie/top_rated")
    Call<Movie_result> getTopRated(@Query("api_key")String api_key);
}
