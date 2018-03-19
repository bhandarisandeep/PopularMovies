package com.example.sandeep.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandeep.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    TextView tv_showData;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    tv_showData=(TextView)findViewById(R.id.tv_showData);
    spinner=(ProgressBar) findViewById(R.id.spinner);


    }
    public class MovieTask extends AsyncTask<URL ,Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL popularUrl = urls[0];
            String movieSearchResults = callHttp(popularUrl);
            return movieSearchResults;
        }
            /*this is the other way of doing without the method callhttp
*   String movieSearchResults = null;
*          try {
*               movieSearchResults = NetworkUtils.getResponseFromHttpUrl(popularUrl);
*               //tv_showData.setText(movieSearchResults);
*          } catch (IOException e) {
*               e.printStackTrace();
*
*           }
* return movieSearchResults;
*/
        @Override
        protected void onPostExecute(String movieSearchReults) {
           spinner.setVisibility(View.INVISIBLE);
            if(movieSearchReults!=null && !movieSearchReults.equals("")){
                tv_showData.setText(movieSearchReults);

            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ItemOfMenu=item.getItemId();
        if(ItemOfMenu==R.id.mostPopular)
        {
            Context context=MainActivity.this;
            //Toast.makeText(this, "You clicked most popular", Toast.LENGTH_SHORT).show();
            URL popularMoviesUrl = NetworkUtils.urlForPopular();
            tv_showData.setText(popularMoviesUrl.toString()+"\n\n\n");
            //tv_showData.setText(callHttp(popularMoviesUrl).toString());  // NOTES: By using this we will get a NetworkOnMainthread error which will solve by using AsyncTask
            new MovieTask().execute(popularMoviesUrl);

        }
        if (ItemOfMenu==R.id.topRated)
        {
            Context context=MainActivity.this;
            Toast.makeText(this, "you clicked top rated", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);

}
/*

this method(callhttp) is used for to get the json data from the url
*/
        public String callHttp(URL fullUrl){
        String SearchResults = null;
        try {
        SearchResults = NetworkUtils.getResponseFromHttpUrl(fullUrl);

            } catch (IOException e) {
                e.printStackTrace();
                    }
                return SearchResults;

        }

}
