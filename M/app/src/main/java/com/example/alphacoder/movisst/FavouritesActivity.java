package com.example.alphacoder.movisst;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.alphacoder.movisst.Model.Movie;
import com.example.alphacoder.movisst.Model.Movie_result;
import com.example.alphacoder.movisst.Model.Session;
import com.example.alphacoder.movisst.Model.User;
import com.example.alphacoder.movisst.Network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesActivity extends AppCompatActivity {
    ArrayList<Movie>MovieList;
    Movie_result movie_result;
    MovieAdapter adapter;
    GridView gr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        MovieList=new ArrayList<Movie>();

        String accesstoken=get_token();
        final String api_key=getResources().getString(R.string.api_key);

        adapter=new MovieAdapter(this,MovieList);

        gr=(GridView)findViewById(R.id.gridView2);
        gr.setAdapter(adapter);


        Call<Session> get_session_id= ApiClient.getInterface().getSessionId(api_key, accesstoken);
        get_session_id.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if(response.isSuccessful())
                {
                    Session session=response.body();
                    final String session_id=session.session_id;

                    Toast.makeText(FavouritesActivity.this, session_id, Toast.LENGTH_SHORT).show();

                    Call<User> get_id=ApiClient.getInterface().getid(api_key, session_id);

                    get_id.enqueue(new Callback<User>() {
                                       @Override
                                       public void onResponse(Call<User> call, Response<User> response) {
                                           if(response.isSuccessful())
                                           {
                                               Call<Movie_result> favourite_movies=ApiClient.getInterface().getFavourites(response.body().getId(),api_key, session_id);

                                               favourite_movies.enqueue(
                                                       new Callback<Movie_result>() {
                                                           @Override
                                                           public void onResponse(Call<Movie_result> call, Response<Movie_result> response) {


                                                               if (response.isSuccessful()) {
                                                                   movie_result = response.body();
                                                                   Toast.makeText(FavouritesActivity.this, response.body().results.get(0).original_title, Toast.LENGTH_SHORT).show();

                                                                   for(int i=0;i<(movie_result.results.size())&&i<12;i++)
                                                                   {
                                                                       MovieList.add(movie_result.results.get(i));
                                                                   }
                                                                   adapter.notifyDataSetChanged();

                                                               }

                                                           }

                                                           @Override
                                                           public void onFailure(Call<Movie_result> call, Throwable t) {

                                                           }
                                                       }
                                               );
                                           }
                                           else
                                           {

                                           }

                                       }

                                       @Override
                                       public void onFailure(Call<User> call, Throwable t) {

                                       }
                                   }
                    );
                }
                else
                {
                    Toast.makeText(FavouritesActivity.this,response.body().session_id, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(FavouritesActivity.this, "I am deadpool", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String get_token()
    {
        SharedPreferences sp =
                getSharedPreferences("Movisst",
                        Context.MODE_PRIVATE);
        return sp.getString("token","Null");
    }
}
