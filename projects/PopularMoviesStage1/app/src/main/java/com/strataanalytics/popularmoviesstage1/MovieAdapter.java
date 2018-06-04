package com.strataanalytics.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private ArrayList<String> movieList;
    private LayoutInflater  layoutInflater;
    // data is passed into the constructor
    MovieAdapter(Context context, ArrayList<String> movieList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
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
        return movieList.size();
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
            Toast.makeText(view.getContext(), getAdapterPosition() + "",
                    Toast.LENGTH_SHORT).show();

        }
    }

}
