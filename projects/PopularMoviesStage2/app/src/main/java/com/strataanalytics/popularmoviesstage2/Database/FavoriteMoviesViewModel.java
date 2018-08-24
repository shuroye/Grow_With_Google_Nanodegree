package com.strataanalytics.popularmoviesstage2.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class FavoriteMoviesViewModel extends AndroidViewModel{

    private FavoriteMoviesRepository favoriteMoviesRepository;
    private LiveData<List<FavoriteMovies>> fAllFavoriteMovies;



    public FavoriteMoviesViewModel(Application application) {
        super(application);

        favoriteMoviesRepository = new FavoriteMoviesRepository(application);
        fAllFavoriteMovies = favoriteMoviesRepository.getAllFavoriteMovies();
    }


    public LiveData<List<FavoriteMovies>> getAllFavoriteMovies() { return fAllFavoriteMovies; }

    public void insert(FavoriteMovies favoriteMovies) { favoriteMoviesRepository.insert(favoriteMovies);     }

    public void deleteFavoriteMovie(FavoriteMovies favoriteMovies){
        favoriteMoviesRepository.delete(favoriteMovies);
    }

   public FavoriteMovies getMovie(FavoriteMovies favoriteMovies){
        FavoriteMovies fav;
        fav = favoriteMoviesRepository.getFavoriteMovie(favoriteMovies);

        return  fav;
   }
}
