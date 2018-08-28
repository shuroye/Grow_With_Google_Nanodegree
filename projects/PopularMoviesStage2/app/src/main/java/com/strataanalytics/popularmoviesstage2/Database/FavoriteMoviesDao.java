package com.strataanalytics.popularmoviesstage2.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteMoviesDao {

    @Query("SELECT * from favMovie_table ORDER BY fav_movie ASC")
    LiveData<List<FavoriteMovies>> getAllFavMovies();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavMovie(FavoriteMovies favoriteMovies);

  //  @Query("DELETE FROM favMovie_table")
   // void deleteAll();

    @Delete
    void deleteFavorite(FavoriteMovies favoriteMovies);

    @Query("SELECT * from favMovie_table WHERE fav_movie = :str_movie")
           FavoriteMovies getFavoriteMovie(String str_movie);
}
