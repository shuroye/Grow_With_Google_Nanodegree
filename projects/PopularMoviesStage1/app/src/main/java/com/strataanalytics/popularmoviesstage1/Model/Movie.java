package com.strataanalytics.popularmoviesstage1.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie implements Parcelable {

    private int intVote_count;
    private int intId;
    private boolean bol_IsVideo;
    private float fVote_average;
    private String strTitle;
    private float fPopularity;
    private String strPoster_path;
    private boolean bol_IsAdult;
    private String strOverview;
    private String strRelease_date;


    public Movie(
            int vote_count,
            int id,
            boolean isVideo,
            float vote_average,
            String title,
            float popularity,
            String poster_path,
            boolean isAdult,
            String overview,
            String release_date )    {
        this.intVote_count = vote_count;
        this.intId =  id;
        this.bol_IsVideo = isVideo;
        this.fVote_average = vote_average;
        this.strTitle =  title;
        this.fPopularity = popularity;
        this.strPoster_path =  poster_path;
        this.bol_IsAdult = isAdult;
        this.strOverview =  overview;
        this.strRelease_date = release_date;

    }

    public int getIntVote_count() {
        return intVote_count;
    }

    public void setIntVote_count(int intVote_count) {
        this.intVote_count = intVote_count;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public boolean isBol_IsVideo() {
        return bol_IsVideo;
    }

    public void setBol_IsVideo(boolean bol_IsVideo) {
        this.bol_IsVideo = bol_IsVideo;
    }

    public float getfVote_average() {
        return fVote_average;
    }

    public void setfVote_average(float fVote_average) {
        this.fVote_average = fVote_average;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public float getfPopularity() {
        return fPopularity;
    }

    public void setfPopularity(float fPopularity) {
        this.fPopularity = fPopularity;
    }

    public String getStrPoster_path() {
        return strPoster_path;
    }

    public void setStrPoster_path(String strPoster_path) {
        this.strPoster_path = strPoster_path;
    }

    public boolean isBol_IsAdult() {
        return bol_IsAdult;
    }

    public void setBol_IsAdult(boolean bol_IsAdult) {
        this.bol_IsAdult = bol_IsAdult;
    }

    public String getStrOverview() {
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

    /*

       Placelable methods
     */
    //Create Constructor used for the parcel
    public Movie(Parcel parcel){
        intVote_count = parcel.readInt();
        intId = parcel.readInt();
        bol_IsVideo = (Boolean) parcel.readValue(getClass().getClassLoader());
        fVote_average = parcel.readFloat();
        strTitle =  parcel.readString();
        fPopularity = parcel.readFloat();
        strPoster_path = parcel.readString();
        bol_IsAdult = (Boolean)parcel.readValue(getClass().getClassLoader());
        strOverview =  parcel.readString();
        strRelease_date = parcel.readString();

    }

    //creator
    public static final Parcelable.Creator<Movie> CREATOR = new
            Parcelable.Creator<Movie>(){

                @Override
                public Movie createFromParcel(Parcel source) {
                    return null;
                }

                @Override
                public Movie[] newArray(int size) {
                    return new Movie[0];
                }
            };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(intId);
        dest.writeValue(bol_IsVideo);
        dest.writeDouble(fVote_average);
        dest.writeString(strTitle);
        dest.writeDouble(fPopularity);
        dest.writeString(strPoster_path);
        dest.writeValue(bol_IsAdult);
        dest.writeString(strOverview);
        dest.writeString(strRelease_date);

    }


}
