package com.example.alphacoder.movisst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Page extends AppCompatActivity {

    Button top_rated,favourites,now_showing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        top_rated=(Button)findViewById(R.id.top_rated);
        favourites=(Button)findViewById(R.id.favourites);
        now_showing=(Button)findViewById(R.id.now_showing);

        top_rated.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent();
                        i.setClass(getApplicationContext(),Top_Rated.class);
                        startActivity(i);
                    }
                }
        );
        favourites.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent();
                        i.setClass(getApplicationContext(),FavouritesActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}
