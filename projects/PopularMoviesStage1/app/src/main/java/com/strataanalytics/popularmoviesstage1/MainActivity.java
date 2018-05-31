package com.strataanalytics.popularmoviesstage1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.strataanalytics.popularmoviesstage1.Model.Movie;
import com.strataanalytics.popularmoviesstage1.MovieNetworkUtils.MovieNetworkUtils;
import com.strataanalytics.popularmoviesstage1.Utils.GetMovieListingTask;
import com.strataanalytics.popularmoviesstage1.Utils.JsonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String strSortOrder = "";
    public static String strMovies = "";
    private static final String TAG = "MyTask";

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    try {
       new GetMoviesTask().execute();


       // Movie movie = JsonUtils.parseMovieJson(strMovies);

        // data to populate the RecyclerView with

        ArrayList<String> movies1 =  new ArrayList<>();
        movies1.add("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
        movies1.add("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
        movies1.add("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
        movies1.add("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
        movies1.add("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
        movies1.add("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");


        // set up the RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = findViewById(R.id.movies_rv);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter(this, movies1);
        recyclerView.setAdapter(movieAdapter);
    }catch (Exception e){
        e.printStackTrace();
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_settings,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sort_top_rating_id:
                strSortOrder = item.getTitle().toString();
                return true;
            case R.id.sort_most_popular_id:
                strSortOrder = item.getTitle().toString();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class GetMoviesTask extends AsyncTask<String,String,String>{

        public GetMoviesTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=6934f53708a2aa88621270ea9c7bc940");
                strMovies =  MovieNetworkUtils.getMovies(url);
                System.out.println(strMovies);

                //return  strMovies;
            }catch (IOException e){
                Log.d(TAG,strMovies);
                return null;
            }
            return  strMovies;
        }
    }



}

