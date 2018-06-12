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

import com.strataanalytics.popularmoviesstage1.Data.GetMoviePreferences;
import com.strataanalytics.popularmoviesstage1.Model.Movie;
import com.strataanalytics.popularmoviesstage1.MovieNetworkUtils.MovieNetworkUtils;
import com.strataanalytics.popularmoviesstage1.Utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myTask";
    static String strUrl = "";
    static int movieOrder = 0;


   static MovieAdapter movieAdapter;
   RecyclerView recyclerView;
   static GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //get the list of movies. Default is Popular movies
            loadMovies();
        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }
    public class MovieTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            MovieNetworkUtils movieNetworkUtils = new MovieNetworkUtils(strUrl);
            String results = "";
            //get the list of movies (JSON) from the server
            try {
               results   = movieNetworkUtils.getMovies();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPreExecute() {
            //movieAdapter = new MovieAdapter(MainActivity.this,  image_list);

        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

        @Override
        protected void onPostExecute(String s) {

            try {

                ArrayList<String> image_list;

                //Process Server data
                JsonUtils jsonUtils = new JsonUtils();
                image_list =  jsonUtils.parseMovieJson(s);

                //populate UI
                gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                recyclerView = findViewById(R.id.movies_rv);
                recyclerView.setLayoutManager(gridLayoutManager);
                movieAdapter = new MovieAdapter(MainActivity.this, image_list);
                recyclerView.setAdapter(movieAdapter);
                movieAdapter.notifyDataSetChanged();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_activity_settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected_id = item.getItemId();

        if(itemSelected_id == R.id.sort_top_rating_id) {
            movieOrder = 1;
            item.setChecked(true);
            loadMovies();
            return true;
        }else if (itemSelected_id == R.id.settings_id){
             ;//do nothing

        }else{
            movieOrder = 0;
            item.setChecked(true);
            loadMovies();
        }
        movieAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }


    public void loadMovies(){
        strUrl = GetMoviePreferences.setPreferedMovieOrder(this,movieOrder);
        MovieTask movieTask = new MovieTask();
        movieTask.execute(strUrl);

    }
}


