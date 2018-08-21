package com.strataanalytics.popularmoviesstage2.MovieNetworkUtils;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the movie server
 * and process the results for the calling modules.
 */
public class MovieNetworkUtils {
    private String url;

    public MovieNetworkUtils(String url) {
        this.url = url;
    }

    public  String getMovies() throws IOException {

        URL url = new URL(this.url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //get movies listing
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {

                return null;
            }
        }catch (UnknownHostException e){
            System.out.println(e.getMessage());
            return  url.toString();
        } finally {
            urlConnection.disconnect();
        }
    }
}
