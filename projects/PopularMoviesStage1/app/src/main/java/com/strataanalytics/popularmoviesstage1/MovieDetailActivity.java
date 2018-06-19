package com.strataanalytics.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.strataanalytics.popularmoviesstage1.Model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        this.setTitle("MovieDetail");


        Intent intent = getIntent();
       // Integer pos = intent.getIntExtra("POSTER_INDEX", 0);
       Movie movie = intent.getParcelableExtra("MOVIE_DETAIL");

        TextView title =  findViewById(R.id.title_TextView);

       // Log.d("PLX", movie.getStrOverview());
      String mTitle = movie.getTitle();
       title.setText(mTitle + "");

    }
}
