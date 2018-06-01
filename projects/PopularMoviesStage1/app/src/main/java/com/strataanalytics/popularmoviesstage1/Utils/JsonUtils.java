package com.strataanalytics.popularmoviesstage1.Utils;

import com.strataanalytics.popularmoviesstage1.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
     public static Movie parseMovieJson(String json) throws NullPointerException{
         final String strResult  = "results";
         final String mainObj     = "object";
         final String poster_path     = "poster_path";


         Movie movie = new Movie();

         if(json != null) {
             try {
                String strBaseUrl = "http://image.tmdb.org/t/p/w342/";

                 JSONObject jsonObject = new JSONObject(json);

                 JSONObject object = jsonObject.getJSONObject(mainObj);

                 //Get Results Array
                 ArrayList<String> resultArray = new ArrayList<>();
                 JSONArray resultJSONArray = object.getJSONArray(strResult);

                 //Process results and get the image path
                 for (int i = 0; i < resultJSONArray.length(); i++){
                     JSONObject obj = resultJSONArray.getJSONObject(i);
                     strBaseUrl = strBaseUrl + obj.getString(poster_path);
                     resultArray.add(strBaseUrl);
                 }
                 movie.setImage_list(resultArray);

             } catch (JSONException e) {
                 e.printStackTrace();

             }
             return movie;
         }else {
             return null;
         }

     }
}
