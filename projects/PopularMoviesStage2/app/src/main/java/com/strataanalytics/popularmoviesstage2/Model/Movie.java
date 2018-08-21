package com.strataanalytics.popularmoviesstage2.Model;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;


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
    private String runtime;
    private List<String> videoList;
    private List<String> reviewList;


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
            String release_date,
            String runtime,
            List<String> videoList,
            List<String> reviewList
            )    {
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
        this.runtime = runtime;
        this.videoList = videoList;
        this.reviewList = reviewList;

    }

    public int getIntVote_count() {
        return intVote_count;
    }
    public int getIntId() {return intId;    }
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
    public String getRuntime() { return runtime;    }
    public List<String> getvideoList() { return videoList; }
    public List<String> getReviewList() { return reviewList; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.intVote_count);
        dest.writeInt(this.intId);
        dest.writeByte(this.bol_IsVideo ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.fVote_average);
        dest.writeString(this.strTitle);
        dest.writeFloat(this.fPopularity);
        dest.writeString(this.strPoster_path);
        dest.writeByte(this.bol_IsAdult ? (byte) 1 : (byte) 0);
        dest.writeString(this.strOverview);
        dest.writeString(this.strRelease_date);
        dest.writeString(this.runtime);
        dest.writeStringList(this.videoList);
        dest.writeStringList(this.reviewList);
    }

    protected Movie(Parcel in) {
        this.intVote_count = in.readInt();
        this.intId = in.readInt();
        this.bol_IsVideo = in.readByte() != 0;
        this.fVote_average = in.readFloat();
        this.strTitle = in.readString();
        this.fPopularity = in.readFloat();
        this.strPoster_path = in.readString();
        this.bol_IsAdult = in.readByte() != 0;
        this.strOverview = in.readString();
        this.strRelease_date = in.readString();
        this.runtime = in.readString();
        this.videoList = in.createStringArrayList();
        this.reviewList = in.createStringArrayList();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
