package com.strataanalytics.popularmoviesstage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.strataanalytics.popularmoviesstage2.Data.GetMoviePreferences;
import com.strataanalytics.popularmoviesstage2.Database.FavoriteMovies;
import com.strataanalytics.popularmoviesstage2.Database.FavoriteMoviesViewModel;
import com.strataanalytics.popularmoviesstage2.MovieNetworkUtils.MovieAsyncResponse;
import com.strataanalytics.popularmoviesstage2.Utils.JsonUtils;
import org.json.JSONArray;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAsyncResponse   {


    static int movieOrder = 0;
    static JSONArray jsonArray;
    static boolean bolFav_view;


    MovieAdapter movieAdapter;
    FavoriteMoviesAdapter favoriteMoviesAdapter;
    RecyclerView recyclerView;
    static GridLayoutManager gridLayoutManager;
    FavoriteMoviesViewModel favoriteMoviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Pop Movies");
        bolFav_view = false;
        loadMovies();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_settings, menu);
        favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected_id = item.getItemId();

        if(itemSelected_id == R.id.sort_top_rating_id) {
            this.setTitle("Pop Movies - top rated");
            movieOrder = 1;
            item.setChecked(true);
            loadMovies();
            return true;
        }else if (itemSelected_id == R.id.settings_id){
            movieOrder = -1;

        }else if (itemSelected_id == R.id.fav_menu){

            this.setTitle("Favorite Movies");
            bolFav_view = true;
        //   favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);

           //create the observer for the favorite movies
            favoriteMoviesViewModel.getAllFavoriteMovies().observe(this, new Observer<List<FavoriteMovies>>() {
                @Override
                public void onChanged(@Nullable List<FavoriteMovies> favoriteMovies) {
                    if (favoriteMovies != null) {
                        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                        recyclerView = findViewById(R.id.movies_rv);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        favoriteMoviesAdapter = new FavoriteMoviesAdapter(MainActivity.this, favoriteMovies);
                        recyclerView.setAdapter(favoriteMoviesAdapter);
                        favoriteMoviesAdapter.notifyDataSetChanged();



                    }else {
                        Toast.makeText(getApplicationContext(), "Favorite is empty",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }else{
            this.setTitle("Pop Movies");
            movieOrder = 0;
            item.setChecked(true);
            loadMovies();
        }
        movieAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPostExecute(String Result) {
        try {

            List<String> image_list;

            //Process Server data
            JsonUtils jsonUtils = new JsonUtils();
            image_list =  jsonUtils.parseMovieJson(Result);
            jsonArray  =   jsonUtils.getResultJSONArray();

            //populate UI
            gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
            recyclerView = findViewById(R.id.movies_rv);
            recyclerView.setLayoutManager(gridLayoutManager);
            movieAdapter = new MovieAdapter(MainActivity.this, image_list,jsonArray);
            recyclerView.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void loadMovies(){

        String strUrl;
        GetMoviePreferences getMoviePreferences = new GetMoviePreferences();

        strUrl = getMoviePreferences.getMovieURL(movieOrder);

        LaunchBackGroundTask launchBackGroundTask = new LaunchBackGroundTask(this, strUrl);
        launchBackGroundTask.launch();
    }


}


