package com.strataanalytics.popularmoviesstage1.Data;

import android.content.Context;

public class GetMoviePreferences {

  private static final  String my_api_key = "";
   //By Most Popular Moview
    private static final String DEFAULT_MOVIE_ORDER = "https://api.themoviedb.org/3/movie/popular?api_key=" + my_api_key;


    //By Top Rating
    private static final String PREFERED_MOVIE_ORDER = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + my_api_key;

   public static String setPreferedMovieOrder(Context context, int movieOrder){
        int movie_order;
        movie_order = movieOrder;

        if (movie_order == 1){
           return   getPreferedMovieOrder();
        }else {
           return getDefaultMovieOrder();
        }
   }

   private static String getDefaultMovieOrder(){
       return DEFAULT_MOVIE_ORDER;
   }

    private static String getPreferedMovieOrder(){
        return PREFERED_MOVIE_ORDER;
    }

}