package com.strataanalytics.popularmoviesstage2;


import com.strataanalytics.popularmoviesstage2.MovieNetworkUtils.MovieAsyncResponse;
import com.strataanalytics.popularmoviesstage2.MovieNetworkUtils.MovieTasks;

public class LaunchBackGroundTask {
    private String netWorkUrl;
    private MovieAsyncResponse response;

    LaunchBackGroundTask(MovieAsyncResponse response, String netWorkUrl) {
        this.netWorkUrl = netWorkUrl;
        this.response = response;
    }

    public void launch(){

        try{
            MovieTasks movieTasks = new MovieTasks(response, netWorkUrl);
            movieTasks.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
