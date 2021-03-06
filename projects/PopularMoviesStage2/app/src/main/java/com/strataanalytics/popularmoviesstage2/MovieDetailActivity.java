package com.strataanalytics.popularmoviesstage2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.strataanalytics.popularmoviesstage2.Database.FavoriteMovies;
import com.strataanalytics.popularmoviesstage2.Database.FavoriteMoviesViewModel;
import com.strataanalytics.popularmoviesstage2.Model.Movie;



public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;
    public RecyclerView recyclerView;
    private MovieDetailVideoAdapter adapter;
    public String strBaseUrl = "http://image.tmdb.org/t/p/w342/";

    private FavoriteMoviesViewModel favoriteMoviesViewModel;
    public  TextView mov_fav_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        this.setTitle("MovieDetail");



         favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);
         movie = intent.getParcelableExtra("MOVIE_DETAIL");

        TextView title_tv = findViewById(R.id.title_TextView);
        ImageView imageView = findViewById(R.id.image_iv);
        TextView release_dt_tv = findViewById(R.id.release_dt_tv);
        TextView mov_runtime_tv = findViewById(R.id.movie_duration_tv);
         mov_fav_tv = findViewById(R.id.movie_fav_tv);
        TextView mov_overview_tv = findViewById(R.id.movie_overview_tv);
        TextView movie_rating_tv = findViewById(R.id.movie_rating_tv);
        TextView trailer_tv = findViewById(R.id.trailer_txt);

         recyclerView = findViewById(R.id.movie_trailers_recycler_v);
         String strFAV = strBaseUrl + movie.getStrPoster_path();

        if(movie != null){
            String runtime = movie.getRuntime();
            String mTitle = movie.getTitle();
            title_tv.setText(mTitle);
            String vote_average = movie.getVote_average()+getString(R.string.slash)+ 10;
            Picasso.with(getApplicationContext())
                    .load(strBaseUrl + movie.getStrPoster_path())
                    .into(imageView);
            release_dt_tv.setText(movie.getRelease_date().substring(0,4));
            mov_runtime_tv.setText(runtime);
            movie_rating_tv.setText(vote_average);


            mov_overview_tv.setText(movie.getOverview());
            FavoriteMovies result;
            try{

                 result = favoriteMoviesViewModel.vFavoriteMovie(new FavoriteMovies(strFAV,movie.getIntId()));
                if(result != null) {
                    //Movie does not exist in favorites
                    String strRemoveFav = getString(R.string.removeFav);
                    mov_fav_tv.setText(strRemoveFav);
                    removeFavoriteMovie(new FavoriteMovies(strFAV, movie.getIntId()));
                }else{
                    String strMarkAsFav = getString(R.string.MAF);
                    mov_fav_tv.setText(strMarkAsFav);
                    addFavoriteMovie(new FavoriteMovies(strFAV, movie.getIntId()));
                }

            }catch (Exception e){
                 e.printStackTrace();
            }
            //set trailer text if there is a trailer
            if(movie.getvideoList() != null){
                String trailerHeader = getString(R.string.Trailers);
                trailer_tv.setText(trailerHeader);
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MovieDetailVideoAdapter(this, movie.getvideoList());
            recyclerView.setAdapter(adapter);



        }else {
            System.out.println(getString(R.string.SysErr));

        }
    }

    public void addFavoriteMovie(final FavoriteMovies favoriteMovies){

        mov_fav_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FavoriteMovies newFav;
                    newFav = favoriteMovies;
                    favoriteMoviesViewModel.insert(newFav);

                    Toast.makeText(getApplicationContext(), R.string.FavAdd,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage() ,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void removeFavoriteMovie(final FavoriteMovies favoriteMovies){

        mov_fav_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FavoriteMovies newFav;
                    newFav = favoriteMovies;
                    favoriteMoviesViewModel.deleteFavoriteMovie(newFav);
                    Toast.makeText(getApplicationContext(), R.string.FavRemove,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage() ,Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_movie_reviews_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId() == R.id.reviews_menu_id) {
           Intent intent = new Intent();
           intent.setClass(this.getApplicationContext(), MovieDetailReviewsActivity.class);
           intent.putExtra(getString(R.string.Detail_Package), movie);
           this.startActivity(intent);
       }
       adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }


}

