package com.strataanalytics.popularmoviesstage2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public  class MovieDetailVideoAdapter extends RecyclerView.Adapter <MovieDetailVideoAdapter.ViewHolder> {

    private List<String> trailerList;
    private  Context context;


    private LayoutInflater  layoutInflater;
    // data is passed into the constructor
     MovieDetailVideoAdapter(Context context, List<String> trailerList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.trailerList = trailerList;
        this.context = context;
     }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MovieDetailVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movie_detail_trailors_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the Image in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         final String strReview = trailerList.get(position);
         String[] split = strReview.split(":");
         final  String trailerID = split[0];
         final String trailerName = split[1];

         holder.trailer_tv.setText(trailerName);



        holder.imageButton.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {

                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+trailerID));
                 intent.putExtra("VIDEO_ID", trailerID);
                context.startActivity(intent);
             }

        });
    }

    //total number of rows in list
    @Override
    public int getItemCount() {

        return trailerList == null? 0:  trailerList.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder {

        TextView trailer_tv;
        View vDivider;
        ImageButton imageButton;

        private ViewHolder(View itemView) {
            super(itemView);

            imageButton = itemView.findViewById(R.id.btnPlay);
            trailer_tv = itemView.findViewById(R.id.trailer_id);
            vDivider = itemView.findViewById(R.id.trailer_divider_id);

        }

    }

}
