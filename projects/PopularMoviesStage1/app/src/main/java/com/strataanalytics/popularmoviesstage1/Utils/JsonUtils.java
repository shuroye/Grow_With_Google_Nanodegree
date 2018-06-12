package com.strataanalytics.popularmoviesstage1.Utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;



public class JsonUtils {

    public  ArrayList<String> parseMovieJson(String json){
        final String strResult   = "results";
        final String poster_path = "poster_path";

        ArrayList<String> resultArray = new ArrayList<>();

        try {

            String strBaseUrl = "http://image.tmdb.org/t/p/w342/";

            JSONObject jsonObject = new JSONObject(json);

            //Get Results Array

            JSONArray resultJSONArray = jsonObject.getJSONArray(strResult);

            //Process results and get the image path
            for (int i = 0; i < resultJSONArray.length(); i++){
                JSONObject obj = resultJSONArray.getJSONObject(i);
                resultArray.add(resultArray.size(),strBaseUrl + obj.getString(poster_path ));
            }

        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return resultArray;
    }
}

