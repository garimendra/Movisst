package com.example.alphacoder.movisst;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alphacoder.movisst.Model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alphacoder on 27/03/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    Context context;
    ArrayList<Movie> MovieList;
    public static class ViewHolder
    {
        ImageView top;
        TextView bottom;
    }

    MovieAdapter(Context context,ArrayList<Movie> objects)
    {
        super(context,0,objects);
        this.context=context;
        this.MovieList=objects;
    }
    public View getView(int position,View convertView ,ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=View.inflate(context,R.layout.movie_layout,null);
            ViewHolder vh=new ViewHolder();
            ImageView top=(ImageView)convertView.findViewById(R.id.poster);
            TextView bottom=(TextView)convertView.findViewById(R.id.moviename);
            vh.top=top;
            vh.bottom=bottom;
            convertView.setTag(vh);
        }
        ViewHolder vh=(ViewHolder)convertView.getTag();
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185"+MovieList.get(position).poster_path).fit().into(vh.top);
        vh.bottom.setText(MovieList.get(position).original_title);

        return convertView;
    }
}
