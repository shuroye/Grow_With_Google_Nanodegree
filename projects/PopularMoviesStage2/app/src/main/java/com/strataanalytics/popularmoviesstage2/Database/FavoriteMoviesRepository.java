package com.strataanalytics.popularmoviesstage2.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FavoriteMoviesRepository{

    private final FavoriteMoviesDao fMoviesDao;
    private LiveData<List<FavoriteMovies>> mAllFavoriteMovies;



    FavoriteMoviesRepository(Application application) {

        FavoriteMoviesRoomDatabase db = FavoriteMoviesRoomDatabase.getDatabase(application);
        fMoviesDao = db.favoriteMoviesDao();
        mAllFavoriteMovies = fMoviesDao.getAllFavMovies();

    }

    public LiveData<List<FavoriteMovies>> getAllFavoriteMovies() {

        return mAllFavoriteMovies;
    }

    public FavoriteMovies getFavoriteMovie(FavoriteMovies favoriteMovies){
        List<FavoriteMovies>  movieList;
        movieList = fMoviesDao.getFavoriteMovie(favoriteMovies.getFav());
       return (FavoriteMovies) movieList;
    }

    public void insert (FavoriteMovies favoriteMovies) {
        new insertAsyncTask(fMoviesDao).execute(favoriteMovies);
    }

    public void delete(FavoriteMovies favoriteMovies){
        new deleteAsyncTask(fMoviesDao).execute(favoriteMovies);
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteMovies, Void, Void> {

        private FavoriteMoviesDao fAsyncTaskDao;

        insertAsyncTask(FavoriteMoviesDao dao) {
            fAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteMovies... params) {
            fAsyncTaskDao.insertFavMovie(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<FavoriteMovies, Void, Void> {

        private FavoriteMoviesDao fAsyncTaskDao;

        deleteAsyncTask(FavoriteMoviesDao dao) {
            fAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteMovies... params) {
            fAsyncTaskDao.deleteFavorite(params[0]);
            return null;
        }
    }
}
