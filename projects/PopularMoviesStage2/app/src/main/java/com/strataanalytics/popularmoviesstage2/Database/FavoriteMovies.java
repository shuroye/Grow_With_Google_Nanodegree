package com.strataanalytics.popularmoviesstage2.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favMovie_table")
public class FavoriteMovies {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "fav_movie")
    private String mFav;



    @ColumnInfo(name = "fav_movie_pos")
    private int mFavPos;

    public FavoriteMovies(@NonNull String mFav, int mFavPos) {

        this.mFav = mFav;
        this.mFavPos = mFavPos;
    }

    @NonNull
    public String getFav() {

        return this.mFav;
    }



    public int getFavPos() {
        return mFavPos;
    }
}
