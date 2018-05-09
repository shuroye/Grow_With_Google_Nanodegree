package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

/*
    Student/Developer: Huroye (Roy) Scott
    Project: Grow With Google Nanodegree Sandwich Projecct

 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        } else {
            int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
            if (position == DEFAULT_POSITION) {
                // EXTRA_POSITION not found in intent
                closeOnError();
            }else {

                String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
                String json = sandwiches[position];

                //  Sandwich sandwich = null;
                Sandwich sandwich = JsonUtils.parseSandwichJson(json);

                if (sandwich == null) {
                    // Sandwich data unavailable
                    closeOnError();
                }else {
                    populateUI(sandwich);
                }
            }
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich selected_sandwich)  {

        //image
        ImageView ingredientsIv;
        ingredientsIv = findViewById(R.id.image_iv);

        Picasso.with(this)
                .load(selected_sandwich.getImage())
                .into(ingredientsIv);

        setTitle(selected_sandwich.getMainName());

        //Sandich alsoKnownAs UI element
        TextView aka_tv =  findViewById(R.id.also_known_tv);
        List<String> aka_list = selected_sandwich.getAlsoKnownAs();
        String str_akaList = processList(aka_list);
        aka_tv.setText(str_akaList);
        aka_tv.setText(str_akaList);

        //Sandwish placeOfOrigin UI element
        TextView placeOfOrigin_tv =  findViewById(R.id.origin_tv) ;
        placeOfOrigin_tv.setText(selected_sandwich.getPlaceOfOrigin());

        //Sandwich descrition UI element
        TextView description_tv =  findViewById(R.id.description_tv) ;
        description_tv.setText(selected_sandwich.getDescription());

        //Sandwich ingredients UI element
        TextView ing_tv =  findViewById(R.id.ingredients_tv);
        List<String> ing_list = selected_sandwich.getIngredients();
        String str_ingList = processList(ing_list);
        ing_tv.setText(str_ingList);
     }

     private String processList(List<String> list){
        /*
            processes List items into a single string
        */

         String processedList;
        if (list.isEmpty()){
            processedList = "N/A";
        }else {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i));
                stringBuilder.append("\n");
            }
            processedList = stringBuilder.toString();
        }
         return processedList;
    }
}
