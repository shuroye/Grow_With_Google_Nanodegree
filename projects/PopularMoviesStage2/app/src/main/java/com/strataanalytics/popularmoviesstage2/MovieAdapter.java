package com.strataanalytics.popularmoviesstage2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.strataanalytics.popularmoviesstage2.Data.GetMoviePreferences;
import com.strataanalytics.popularmoviesstage2.Database.FavoriteMoviesDao;
import com.strataanalytics.popularmoviesstage2.Database.FavoriteMoviesViewModel;
import com.strataanalytics.popularmoviesstage2.Model.Movie;
import com.squareup.picasso.Picasso;
import com.strataanalytics.popularmoviesstage2.MovieNetworkUtils.MovieAsyncResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public  class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private List<String> movieList;
    private JSONArray movieArray;
    private int layoutPOS;

    private LayoutInflater  layoutInflater;
    private  Context context;
    // data is passed into the constructor
     MovieAdapter(Context context,List<String> movieList, JSONArray array) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
        this.movieArray = array;
        this.context = context;
     }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(  @NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movies_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the Image in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String strMovie = movieList.get(position);

        Context context = holder.imageView.getContext();
        Picasso.with(context)
                .load(strMovie)
                .into(holder.imageView);
    }

    //total number of rows in list
    @Override
    public int getItemCount() {

        return movieList == null? 0:  movieList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;

        private ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_movies_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                String strUrl;
                int movieOrder = 2;
                final int id = movieArray.getJSONObject(getLayoutPosition()).getInt("id");

                GetMoviePreferences getMoviePreferences = new GetMoviePreferences(id);

                MovieAsyncResponse response = new MovieAsyncResponse() {
                    @Override
                    public void onPostExecute(String Result) {

                        try {
                            JSONObject object = new JSONObject(Result);
                            layoutPOS = id;//getLayoutPosition();
                            //get Reviews
                            JSONObject jsonObjectGetReviews = object.optJSONObject("reviews");
                            JSONArray reviewsArray = jsonObjectGetReviews.getJSONArray("results");

                            List<String> movieReviewList = new ArrayList<>();

                            StringBuilder builder = new StringBuilder();

                            for (int i = 0; i < reviewsArray.length(); i++){
                                JSONObject reviewObj = reviewsArray.getJSONObject(i);


                                builder.append("\n Author: ")
                                        .append(reviewObj.getString("author"))
                                        .append("\n")
                                        .append(reviewObj.getString("content"))
                                        .append("\n");
                            }

                            //get Videos info
                            List<String> videoList = new ArrayList<>();
                            JSONObject jsonObjectGetVideos = object.optJSONObject("videos");
                            JSONArray videosArray = jsonObjectGetVideos.getJSONArray("results");



                            for (int i = 0; i < videosArray.length(); i++){
                                JSONObject videoObj = videosArray.getJSONObject(i);

                                //add Trailers only
                                String strVideoType = videoObj.getString("type");
                                StringBuilder stringBuilder = new StringBuilder();
                                String name = videoObj.getString("name");
                                String videoKey = videoObj.getString("key");
                                stringBuilder.append(videoKey)
                                        .append(":")
                                        .append(name);
                               if(strVideoType.toLowerCase().equals("trailer")) {
                                    videoList.add(videoList.size(), stringBuilder.toString());
                                }
                            }

                            //get the objects
                            int vote_count = object.getInt("vote_count");
                            int id = object.getInt("id");
                            boolean isVideo = object.getBoolean("video");
                            float vote_average = object.getInt("vote_average");
                            String title = object.getString("title");
                            float popularity = object.getInt("popularity");
                            String poster_path = object.getString("poster_path");
                            boolean isAdult = object.getBoolean("adult");
                            String overview = object.getString("overview");
                            String release_date = object.getString("release_date");
                            String runtime = object.getString("runtime");
                            movieReviewList.add(builder.toString());


                            Movie movie = new Movie(
                                    vote_count,
                                    id,
                                    isVideo,
                                    vote_average,
                                    title,
                                    popularity,
                                    poster_path,
                                    isAdult,
                                    overview,
                                    release_date,
                                    runtime,
                                    videoList,
                                    movieReviewList

                            );
                            Log.d("POS", layoutPOS + "");
                            //Call MovieDetail with the movie id
                            launchMovieDetailActivity(movie);

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                };


                strUrl = getMoviePreferences.getMovieURL(movieOrder);
                LaunchBackGroundTask launchBackGroundTask = new LaunchBackGroundTask(response, strUrl);

                launchBackGroundTask.launch();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void launchMovieDetailActivity(Movie movie){
         if (movie != null) {
            Intent intent = new Intent();
            intent.setClass(context.getApplicationContext(), MovieDetailActivity.class);

            intent.putExtra("MOVIE_DETAIL", movie);
            context.startActivity(intent);
        }else {
            Log.d("MOVIE", "Error");
        }
    }

}
