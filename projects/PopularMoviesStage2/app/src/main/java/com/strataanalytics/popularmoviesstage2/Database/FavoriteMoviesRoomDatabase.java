package com.strataanalytics.popularmoviesstage2.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {FavoriteMovies.class}, version = 5, exportSchema = false)
public abstract class FavoriteMoviesRoomDatabase extends RoomDatabase{


    public abstract FavoriteMoviesDao favoriteMoviesDao();

    private static FavoriteMoviesRoomDatabase INSTANCE;

    static FavoriteMoviesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteMoviesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteMoviesRoomDatabase.class, "favoriteMovies_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

     private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final FavoriteMoviesDao fDao;



        PopulateDbAsync(FavoriteMoviesRoomDatabase db) {
            fDao = db.favoriteMoviesDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {
         //  FavoriteMovies f = new FavoriteMovies("http://image.tmdb.org/t/p/w342//7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", 1);
          //  fDao.deleteFavorite(f);
         //   fDao.deleteAll();
           //  FavoriteMovies[] str = fDao.getFavoriteMovie(f.getFav());
           // Log.d("TEST2", str[0].getFavPos() + "");
            return null;
        }

     }

}
