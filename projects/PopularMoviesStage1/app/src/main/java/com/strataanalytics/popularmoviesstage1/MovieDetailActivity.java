package com.strataanalytics.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.strataanalytics.popularmoviesstage1.Model.Movie;


public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        this.setTitle("MovieDetail");

        String strBaseUrl = "http://image.tmdb.org/t/p/w342/";

       Intent intent = getIntent();
       Movie movie = intent.getParcelableExtra("MOVIE_DETAIL");
       TextView title_tv =  findViewById(R.id.title_TextView);
       ImageView imageView = findViewById(R.id.image_iv);
       TextView release_dt_tv = findViewById(R.id.release_dt_tv);
       TextView mov_runtime_tv = findViewById(R.id.movie_duration_tv);
       TextView mov_fav_tv = findViewById(R.id.movie_fav_tv);
       TextView mov_overview_tv = findViewById(R.id.movie_overview_tv);
       TextView movie_rating_tv = findViewById(R.id.movie_rating_tv);


      if(movie != null){

          String mTitle = movie.getTitle();
          title_tv.setText(mTitle);

          Picasso.with(getApplicationContext())
                  .load(strBaseUrl + movie.getStrPoster_path())
                  .into(imageView);
           release_dt_tv.setText(movie.getRelease_date().substring(0,4));
           mov_runtime_tv.setText(120 + getString(R.string.MIN));
           movie_rating_tv.setText(movie.getVote_average()+getString(R.string.slash)+ 10);
           mov_fav_tv.setText(R.string.MAF);
           mov_overview_tv.setText(movie.getOverview());
      }else {
          System.out.println("System Error");

      }


    }
}
