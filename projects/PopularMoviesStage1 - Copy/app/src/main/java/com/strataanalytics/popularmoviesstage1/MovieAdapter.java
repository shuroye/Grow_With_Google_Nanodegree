package com.strataanalytics.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private List<String> movieList;
    private LayoutInflater  layoutInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MovieAdapter(Context context, List<String> movieData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieData;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movies_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the Imaage in each row
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
       String strMovie = movieList.get(position);
       //holder.movieImageView.setImageResource(Integer.parseInt(strMovie));
        holder.textView.setText(strMovie);
    }

    //total number of rows in list
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
      //  ImageView movieImageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
           // movieImageView = itemView.findViewById(R.id.movie_image_id);
            textView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }
    // convenience method for getting data at click position
    String getItem(int id) {
        return movieList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
