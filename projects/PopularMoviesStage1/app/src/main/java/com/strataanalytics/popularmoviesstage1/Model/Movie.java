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

    public Movie(){
      super();
    }
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
    public int getIntId() {
        return intId;
    }
    public boolean getIsVideo() {
        return bol_IsVideo;
    }
    public float getVote_average() {
        return fVote_average;
    }
    public String getTitle() {  return strTitle;  }
    public float getPopularity() {
        return fPopularity;
    }
    public String getStrPoster_path() {
        return strPoster_path;
    }
    public boolean getIsAdult() {
        return bol_IsAdult;
    }
    public String getOverview() {
        return strOverview;
    }
    public String getRelease_date() {
        return strRelease_date;
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
