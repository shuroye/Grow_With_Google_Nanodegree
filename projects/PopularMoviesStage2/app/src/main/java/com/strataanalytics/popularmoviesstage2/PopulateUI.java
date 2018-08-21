package com.strataanalytics.popularmoviesstage2;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;

import java.util.List;

public class PopulateUI {
    MovieAdapter movieAdapter;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private JSONArray jsonArray;
    private List<String> image_list;

    public PopulateUI( List<String> image_list, JSONArray jsonArray){
        this.image_list = image_list;
        this.jsonArray = jsonArray;
    }

}
