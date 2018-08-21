package com.strataanalytics.popularmoviesstage2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.List;


public  class MovieDetailReviewAdapter extends RecyclerView.Adapter <MovieDetailReviewAdapter.ViewHolder> {

    private List<String> reviewsList;

    private LayoutInflater  layoutInflater;
    // data is passed into the constructor
     MovieDetailReviewAdapter(Context context,List<String> reviewsList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.reviewsList = reviewsList;
     }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MovieDetailReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movie_detail_reviews_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the Image in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         String strReview = reviewsList.get(position);
         holder.reviews_tv.setText(strReview);
    }

    //total number of rows in list
    @Override
    public int getItemCount() {

        return reviewsList == null? 0:  reviewsList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView reviews_tv;

        private ViewHolder(View itemView) {
            super(itemView);

            reviews_tv = itemView.findViewById(R.id.reviews_id);
        }


    }



}
