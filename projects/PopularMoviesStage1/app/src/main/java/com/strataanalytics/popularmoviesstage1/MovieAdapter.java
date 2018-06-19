package com.strataanalytics.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.strataanalytics.popularmoviesstage1.Model.Movie;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.List;

public  class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private List<String> movieList;
    private JSONArray movieArray;

    private LayoutInflater  layoutInflater;
    private Context context;

    // data is passed into the constructor
    MovieAdapter(Context context,List<String> movieList, JSONArray array) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
        this.context = context;
        this.movieArray = array;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(  @NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movies_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the Imaage in each row
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
                int pos = getLayoutPosition();
                int vote_count = movieArray.getJSONObject(getLayoutPosition()).getInt("vote_count");
                int id = movieArray.getJSONObject(getLayoutPosition()).getInt("id");
                boolean isVideo = movieArray.getJSONObject(getLayoutPosition()).getBoolean("video");
                float vote_average = movieArray.getJSONObject(getLayoutPosition()).getInt("vote_average");
                String title = movieArray.getJSONObject(getLayoutPosition()).getString("title");
                float popularity = movieArray.getJSONObject(getLayoutPosition()).getInt("popularity");
                String poster_path = movieArray.getJSONObject(getLayoutPosition()).getString("poster_path");
                boolean isAdult = movieArray.getJSONObject(getLayoutPosition()).getBoolean("adult");
                String overview = movieArray.getJSONObject(getLayoutPosition()).getString("overview");
                String release_date = movieArray.getJSONObject(getLayoutPosition()).getString("release_date");



             // Movie movie = new Movie();
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
                 release_date );

               // Log.d("TEST1", movie.getTitle() + "");
                //Call MovieDetail with the movie id
                launchMovieDetailActivity(movie);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

   private void launchMovieDetailActivity(Movie movie){

       Intent intent = new Intent();
       intent.setClass(context.getApplicationContext(),MovieDetailActivity.class);

       intent.putExtra("MOVIE_DETAIL", movie);
       context.startActivity(intent);
   }

}
