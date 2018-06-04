package com.strataanalytics.popularmoviesstage1.Model;

import java.util.ArrayList;

public class Movie {

    private int intVote_count;
    private int intId;
    private boolean bol_IsVideo;
    private float fVote_average;
    private String strTitle;
    private float fPopularity;
    private String strPoster_path;
    private String strOriginal_language;
    private String strOriginal_title;
    private int[] intList_genre_ids;
    private String strBackdrop_path;
    private boolean bol_IsAdult;
    private String strOverview;
    private String strRelease_date;
    private static ArrayList<String> image_list;

    public Movie(){

    }
    public Movie(
            int vote_count,
            int id,
            boolean isVideo,
            float vote_average,
            String title,
            float popularity,
            String poster_path,
            String original_language,
            String original_title,
            int[] list_genre_ids,
            String backdrop_path,
            boolean isAdult,
            String overview,
            String release_date,
            ArrayList<String> image_list){

        this.intVote_count = vote_count;
        this.intId =  id;
        this.bol_IsVideo = isVideo;
        this.fVote_average = vote_average;
        this.strTitle =  title;
        this.fPopularity = popularity;
        this.strPoster_path =  poster_path;
        this.strOriginal_language = original_language;
        this.strOriginal_title = original_title;
        this.intList_genre_ids = list_genre_ids;
        this.strBackdrop_path = backdrop_path;
        this.bol_IsAdult = isAdult;
        this.strOverview =  overview;
        this.strRelease_date = release_date;
        this.image_list = image_list;

    }
    public int getVote_count() {
        return intVote_count;
    }

    public void setVote_count(int intVote_count) {
        this.intVote_count = intVote_count;
    }

    public int getId() {
        return intId;
    }

    public void setId(int intId) {
        this.intId = intId;
    }

    public boolean isVideo() {
        return bol_IsVideo;
    }

    public void setIsVideo(boolean bol_IsVideo) {
        this.bol_IsVideo = bol_IsVideo;
    }

    public float getVote_average() {
        return fVote_average;
    }

    public void setVote_average(float fVote_average) {
        this.fVote_average = fVote_average;
    }

    public String getTitle() {
        return strTitle;
    }

    public void setTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public float getPopularity() {
        return fPopularity;
    }

    public void setPopularity(float fPopularity) {
        this.fPopularity = fPopularity;
    }

    public String getPoster_path() {
        return strPoster_path;
    }

    public void setPoster_path(String strPoster_path) {
        this.strPoster_path = strPoster_path;
    }

    public String getOriginal_language() {
        return strOriginal_language;
    }

    public void setOriginal_language(String strOriginal_language) {
        this.strOriginal_language = strOriginal_language;
    }

    public String getOriginal_title() {
        return strOriginal_title;
    }

    public void setOriginal_title(String strOriginal_title) {
        this.strOriginal_title = strOriginal_title;
    }

    public int[] getList_genre_ids() {
        return intList_genre_ids;
    }

    public void setList_genre_ids(int[] intList_genre_ids) {
        this.intList_genre_ids = intList_genre_ids;
    }

    public String getBackdrop_path() {
        return strBackdrop_path;
    }

    public void setBackdrop_path(String strBackdrop_path) {
        this.strBackdrop_path = strBackdrop_path;
    }

    public boolean isAdult() {
        return bol_IsAdult;
    }

    public void setIsAdult(boolean bol_IsAdult) {
        this.bol_IsAdult = bol_IsAdult;
    }

    public String getOverview() {
        return strOverview;
    }

    public void setStrOverview(String strOverview) {
        this.strOverview = strOverview;
    }

    public String getStrRelease_date() {
        return strRelease_date;
    }

    public void setStrRelease_date(String strRelease_date) {
        this.strRelease_date = strRelease_date;
    }

    public ArrayList<String> getImage_list() {
        return image_list;
    }

    public void setImage_list(ArrayList<String> image_list) {
        this.image_list = image_list;
    }
}
