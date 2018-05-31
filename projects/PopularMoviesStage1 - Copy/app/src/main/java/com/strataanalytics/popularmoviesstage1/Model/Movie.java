package com.strataanalytics.popularmoviesstage1.Model;

public class Movie {
    private  String movieTitle, movieImage;
    private int movieRating, movieID;

    public Movie(){

    }

    public Movie(int id,String image, int rating,String title){
       this.movieID = id;
       this.movieImage = image;
       this.movieRating = rating;
       this.movieTitle = title;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
