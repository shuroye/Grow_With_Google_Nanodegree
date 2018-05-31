package com.strataanalytics.popularmoviesstage1.Utils;

import android.util.Log;

import com.strataanalytics.popularmoviesstage1.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
     public static Movie parseMovieJson(String json){
         final String strResult  = "results";


         Movie movie = new Movie();

         if(json != null) {
             try {
                 String strBaseUrl = "http://image.tmdb.org/t/p/w342/";
                JSONObject jsonObject = new JSONObject(json);

                JSONObject result = jsonObject.getJSONObject(strResult);

                 //get Results - image string only
                 ArrayList <String> movie_image_list = new ArrayList<>();
                 JSONArray movie_array = result.getJSONArray(strResult);
                // JSONArray movie_array = new JSONArray(json);

                 //process Movie list
                 for (int i = 0; i < movie_array.length();i++){
                     JSONObject img_obj = jsonObject.getJSONObject("poster_path");
                     strBaseUrl = strBaseUrl + img_obj;
                     movie_image_list.add(movie_image_list.size(), strBaseUrl);
                 }
                 movie.setImage_list(movie_image_list);

             } catch (JSONException e) {
                 e.printStackTrace();

             }
             return movie;
         }else {
             return null;
         }

     }
}
