package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        final String NAME     = "name";
        final String IMG      = "image";
        final String mainNAME = "mainName";
        final String AKA      = "alsoKnownAs";
        final String POR      = "placeOfOrigin";
        final String DESC     = "description";
        final String ING      = "ingredients";
        final String UNKNOW   = "N/A";

        Sandwich sandwich = new Sandwich();


        if(json != null) {
            try {

                JSONObject jsonObject = new JSONObject(json);

                //get name:mainName
                JSONObject name = jsonObject.getJSONObject(NAME);

                String mName = name.getString(mainNAME);
                sandwich.setMainName(mName);

                //get name:alsoKNownAs
                List <String> aka_list = new ArrayList<>();
                JSONArray aka_array = name.getJSONArray(AKA);

                //process AKA list
                for (int i = 0; i < aka_array.length();i++){
                    aka_list.add(aka_list.size() ,aka_array.getString(i));
                }
                sandwich.setAlsoKnownAs(aka_list);

                //place of Origin
                String placeOfOrigin_str = jsonObject.getString(POR);
                if(placeOfOrigin_str.isEmpty()) {
                    placeOfOrigin_str = UNKNOW;
                    sandwich.setPlaceOfOrigin(placeOfOrigin_str);
                }else {
                    sandwich.setPlaceOfOrigin(placeOfOrigin_str);
                }

                 //Description
                String description_str = jsonObject.getString(DESC);
                if(description_str.isEmpty()){
                    description_str = UNKNOW;
                    sandwich.setDescription(description_str);
                }else {
                    sandwich.setDescription(description_str);
                }


                //get image
                String image_str = jsonObject.getString(IMG);
                sandwich.setImage(image_str);

                //Ingredients
                List <String> ing_list = new ArrayList<>();
                JSONArray ing_array = jsonObject.getJSONArray(ING);

                //process ingredient list
                for (int i = 0; i < ing_array.length();i++){
                    ing_list.add(ing_list.size() ,ing_array.getString(i));
                }
                sandwich.setIngredients(ing_list);

          } catch (JSONException e) {
                e.printStackTrace();
            }
           return sandwich;
        }else {
            return null;
        }
    }

}
