package com.strataanalytics.popularmoviesstage1.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.strataanalytics.popularmoviesstage1.MovieNetworkUtils.MovieNetworkUtils;

import java.io.IOException;
import java.net.URL;


public class GetMovieListingTask {
String strMovies;
    public  class GetMoviesTask extends AsyncTask<String,String,String>{

        public GetMoviesTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=6934f53708a2aa88621270ea9c7bc940");
               // strMovies =  MovieNetworkUtils.getMovies(strUrl);
                System.out.println(strMovies);

               return  strMovies;
            }catch (IOException e){
                Log.i("GETMoveTask",strMovies);
                return null;
            }
            //return  strMovies;
        }
    }
}
