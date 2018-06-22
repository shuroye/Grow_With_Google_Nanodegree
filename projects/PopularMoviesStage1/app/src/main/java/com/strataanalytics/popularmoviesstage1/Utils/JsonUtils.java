package com.strataanalytics.popularmoviesstage1.Utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    private JSONArray resultJSONArray;

    public  List<String> parseMovieJson(String json){
        final String strResult   = "results";
        final String poster_path = "poster_path";

        List<String> resultArray = new ArrayList<>();


        try {

            String strBaseUrl = "http://image.tmdb.org/t/p/w342/";
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);

            //Get Results Array

            resultJSONArray = jsonObject.getJSONArray(strResult);

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

    public JSONArray getResultJSONArray() {
        return resultJSONArray;
    }
}