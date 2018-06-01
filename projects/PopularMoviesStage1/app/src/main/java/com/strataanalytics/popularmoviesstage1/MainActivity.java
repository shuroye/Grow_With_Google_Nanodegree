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
import android.widget.Toast;


import com.strataanalytics.popularmoviesstage1.Model.Movie;
import com.strataanalytics.popularmoviesstage1.MovieNetworkUtils.MovieNetworkUtils;
import com.strataanalytics.popularmoviesstage1.Utils.JsonUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String strSortOrder;
    public  static String strMovies;
    private  static final String TAG = "myTask";

    static String strMovieURL =   "http://api.themoviedb.org/3/movie/popular?api_key=6934f53708a2aa88621270ea9c7bc940";

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
                // data to populate the RecyclerView with
                new GetMoviesTask().execute();

                Movie movies =  JsonUtils.parseMovieJson(strMovies);

                if(movies != null) {

                    // set up the RecyclerView
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                    RecyclerView recyclerView = findViewById(R.id.movies_rv);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    movieAdapter = new MovieAdapter(this, movies.getImage_list());
                    recyclerView.setAdapter(movieAdapter);
                }else {
                    closeOnError();
                }
        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }
    private void closeOnError() {
        Toast.makeText(this, "Movies do not exist!", Toast.LENGTH_LONG).show();
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
                //send request to server and store response in strMovies
                strMovies = MovieNetworkUtils.getMovies(strMovieURL);
               // Log.v(TAG, strMovies);
               // return  strMovies;
            }catch (IOException e){
                return null;
            }
            return strMovies;
        }
    }



}

