package com.strataanalytics.popularmoviesstage1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.strataanalytics.popularmoviesstage1.MovieNetworkUtils.MovieNetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String strSortOrder = "";
    public static String strMovies = "";

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
      //  ArrayList<String> testData = new ArrayList<>();
      //  testData.add("http://image.tmdb.org/t/p/w185//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
       // new getMoviesListTask().execute();
        RecyclerView recyclerView = findViewById(R.id.movies_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(this, animalNames);
        recyclerView.setAdapter(movieAdapter);

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

    private static class getMoviesListTask extends AsyncTask<String,String,String>{

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
                 strMovies  =  MovieNetworkUtils.getMovies(url);


                return  strMovies;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
    }

}

