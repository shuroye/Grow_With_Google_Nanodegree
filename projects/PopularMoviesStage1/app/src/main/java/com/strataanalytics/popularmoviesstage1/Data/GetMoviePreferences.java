package com.strataanalytics.popularmoviesstage1.Data;


import com.strataanalytics.popularmoviesstage1.BuildConfig;

public  class  GetMoviePreferences {

  private static int movie_id = 0;
  private static final  String my_api_key = BuildConfig.APPLICATION_ID;

   //By Most Popular Moview
  private static final String DEFAULT_MOVIE_ORDER = "https://api.themoviedb.org/3/movie/popular?api_key=" + my_api_key;


    //By Top Rating
  private static final String PREFERED_MOVIE_ORDER = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + my_api_key;

  private static final String MOVIE_DETAIL_REQUEST_URL = "https://api.themoviedb.org/3/movie/"+movie_id+"?api_key=" + my_api_key;

   public static String setPreferedMovieOrder( int movieOrder){
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

    private static int setMovieID(int id){

       if (id == Integer.parseInt(null)) {
           return movie_id = 0;
       }else{
           movie_id = id;
           return movie_id = id;
       }

    }
    private static String getMovieDetailRequestUrl(){
        return MOVIE_DETAIL_REQUEST_URL;
    }

}