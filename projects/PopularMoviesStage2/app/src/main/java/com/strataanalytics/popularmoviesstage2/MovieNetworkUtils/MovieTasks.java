package com.strataanalytics.popularmoviesstage2.MovieNetworkUtils;


import android.os.AsyncTask;

import java.io.IOException;


public  class  MovieTasks extends AsyncTask<String, Void, String>{


    private MovieAsyncResponse movieAsyncResponse;

    private String url;

    //constructor

    public MovieTasks(MovieAsyncResponse movieAsyncResponse, String url){

        this.movieAsyncResponse = movieAsyncResponse;
        this.url = url;

    }

    @Override
    protected String doInBackground(String... strings) {
        MovieNetworkUtils movieNetworkUtils = new MovieNetworkUtils(url);
        String results = "";
        //get the list of movies (JSON) from the server
        try {
            results   = movieNetworkUtils.getMovies();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        movieAsyncResponse.onPostExecute(result);

    }
}

