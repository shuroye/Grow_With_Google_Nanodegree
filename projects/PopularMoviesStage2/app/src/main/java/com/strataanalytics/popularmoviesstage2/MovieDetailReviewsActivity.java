package com.strataanalytics.popularmoviesstage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.strataanalytics.popularmoviesstage2.Model.Movie;

public class MovieDetailReviewsActivity extends AppCompatActivity {
   Movie movie;
   MovieDetailReviewAdapter  adapter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_reviews);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("MOVIE_DETAIL_REVIEWS");

        //Setup Recyclerview
        RecyclerView recyclerView = findViewById(R.id.movie_reviews_recycler_v);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieDetailReviewAdapter(this, movie.getReviewList());
        recyclerView.setAdapter(adapter);
    }
}
