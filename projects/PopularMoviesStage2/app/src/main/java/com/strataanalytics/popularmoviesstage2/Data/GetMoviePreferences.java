package com.strataanalytics.popularmoviesstage2.Data;


import com.strataanalytics.popularmoviesstage2.R;

public  class  GetMoviePreferences {

    private  final  String my_api_key = "YOUR API KEY";
    private final String append_videos_and_reviews = "&append_to_response=videos,reviews";

    private  int movie_id;


    public GetMoviePreferences() {
    }

    public GetMoviePreferences(int id) {
        this.movie_id = id;
    }


    public  String getMovieURL( int movieOrder){
        int movie_order;
        movie_order = movieOrder;
        if (movie_order == 0){
            return getDefaultMovieURL();
        }else if (movie_order == 1){
            return   getPreferedMovieURL();
        }else if (movie_order == 2){
            return getMovieDetailURL();
        }else {
            return getDefaultMovieURL();
        }
    }

    private   String getDefaultMovieURL(){ return "https://api.themoviedb.org/3/movie/popular?api_key=" + my_api_key; }

    private  String getPreferedMovieURL(){ return "https://api.themoviedb.org/3/movie/top_rated?api_key=" + my_api_key; }

    private  String getMovieDetailURL(){return "https://api.themoviedb.org/3/movie/"+movie_id+"?api_key=" + my_api_key+append_videos_and_reviews; }




}
