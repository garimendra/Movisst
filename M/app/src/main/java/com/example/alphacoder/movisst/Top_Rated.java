package com.example.alphacoder.movisst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.alphacoder.movisst.Model.Movie;
import com.example.alphacoder.movisst.Model.Movie_result;
import com.example.alphacoder.movisst.Network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top_Rated extends AppCompatActivity {

    final static String api_key="de3e6b37e4f6eb8ff97b6acb87b35264";

    Movie_result movie_result;
    ArrayList<Movie> MovieList;
    GridView gridView;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top__rated);

        MovieList=new ArrayList<Movie>();

        gridView=(GridView)findViewById(R.id.gridView);

        adapter=new MovieAdapter(this,MovieList);

        gridView.setAdapter(adapter);


        Call<Movie_result> ratedmovies= ApiClient.getInterface().getTopRated(api_key);

        ratedmovies.enqueue(new Callback<Movie_result>() {
            @Override
            public void onResponse(Call<Movie_result> call, Response<Movie_result> response) {
                if(response.isSuccessful()) {
                    //Toast.makeText(Top_Rated.this, "Shit is Full", Toast.LENGTH_SHORT).show();
                    movie_result=response.body();

                    for(int i=0;i<13;i++)
                    if(i!=1)
                    {
                        MovieList.add(movie_result.results.get(i));
                    }
                    adapter.notifyDataSetChanged();

                }
                else {
                    Toast.makeText(Top_Rated.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Movie_result> call, Throwable t) {

            }
        });
    }
}
