package com.example.sandeep.popularmovies.utilities;

/**
 * Created by sawan on 3/18/2018.
 */


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;


/**
 *  SORT_POPULAR is used to sort movies according to popularity
 *  SORT_TOP_RATED is used to sort movies according to top rated
 *  IMAGE_SIZE is th size of poster which is displayed in the app. It is recomended from the instructions to go for this size
 */
public class NetworkUtils {
    //final static String MOVIE_BASE_URL ="http://api.themoviedb.org/3/movie/popular?api_key=";
    final static String API_KEY = "c5c35022248742ba11be0bde409c1caf";   // replace this with your api key
    final static String BASE_URL = "http://api.themoviedb.org/3/movie";   //this is the base url to call for the movie json data
    final static String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    final static String IMAGE_SIZE = "w185";
    final static String SORT_POPULAR="popular";
    final static String SORT_TOP_RATED="top_rated";


    final static String BASE_POPULAR_URL="http://api.themoviedb.org/3/movie/popular";
    final static String BASE_TOP_RATED_URL="http://api.themoviedb.org/3/movie/top_rated";
    final static String PARAM_KEY="api_key";
    final static String FULL_IMAGE_URL="http://image.tmdb.org/t/p/w185";
/*
 * we will be using three methods:
  * one for popular,
  * one for top rated,
  * one for image calls


 */
public  static URL urlForPopular(){
    Uri uri= Uri.parse(BASE_POPULAR_URL).buildUpon()
            .appendQueryParameter(PARAM_KEY,API_KEY).build();


URL url=null;
try{
    url =new URL(uri.toString());
}
catch (MalformedURLException e)
{
    e.printStackTrace();
}

return url;
}


/*
******************************************************************
THIS function/method is used to build the image url but instead of this i used the get image path in the result class
 and pass the remaining url in it and build the url

 Right now in the Adapter class this method call is commented if want to use this uncomment sall the function implementaion and
 uncommnet in the Adapter class as well
******************************************************************

    public  static URL urlForImage(String imagepath){
        Uri uri= Uri.parse(FULL_IMAGE_URL).buildUpon().appendPath(imagepath).build();


        URL url=null;
        try{
            url =new URL(uri.toString());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return url;
    }
*/


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
