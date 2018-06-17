package com.strataanalytics.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public  class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private List<String> movieList;

    private LayoutInflater  layoutInflater;
    private Context context;

    // data is passed into the constructor
    MovieAdapter(Context context,List<String> movieList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
        this.context = context;
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
            List<Integer> movieIds;
            int pos = getLayoutPosition();
           // movieIds = MainActivity.movieId;

          //Call MovieDetail with the movie id
         // launchMovieDetailActivity(333);

        }
    }

   private void launchMovieDetailActivity(int index){

        String movieJSON = MainActivity.movieJSON;
       String strBaseUrl = "http://image.tmdb.org/t/p/w342/";
      try {
          JSONObject jsonObject = new JSONObject(movieJSON);
          JSONArray resultJSONArray = jsonObject.getJSONArray("results");
          JSONObject obj = resultJSONArray.getJSONObject(index);

          //get details
          String test = strBaseUrl + obj.getString("poster_path");
          Log.d("TEST", test);
      }catch (Exception e){
          e.printStackTrace();
      }


       Intent intent = new Intent();
       intent.setClass(context,MovieDetailActivity.class);
       intent.putExtra("movieID", index);

       context.startActivity(intent);
   }

}
