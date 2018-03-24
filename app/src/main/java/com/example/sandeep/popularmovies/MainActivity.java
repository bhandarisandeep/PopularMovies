package com.example.sandeep.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandeep.popularmovies.DataFiles.AdapterForRecyclerView;
import com.example.sandeep.popularmovies.DataFiles.Result;
import com.example.sandeep.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterForRecyclerView.ListItemClickListener
{

    private static RecyclerView movieRecyclerView;
    private static AdapterForRecyclerView movieAdapter;
    GridLayoutManager layoutForMovie;
    private int columns;
    public static TextView tv_showData;
    public static   ProgressBar spinner;
    private static final String TAG1 = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       columns=2;
        // columns = getResources().getInteger(R.integer.movie_column);
       movieRecyclerView=(RecyclerView)findViewById(R.id.rv_movie_detail);
       tv_showData=(TextView)findViewById(R.id.tv_showErrorMes);
       spinner=(ProgressBar) findViewById(R.id.spinner);

        layoutForMovie=new GridLayoutManager(this,columns, LinearLayoutManager.VERTICAL,false);
        movieRecyclerView.setLayoutManager(layoutForMovie);
        movieRecyclerView.setHasFixedSize(true);
       // movieAdapter=new AdapterForRecyclerView(this);
        movieAdapter=new AdapterForRecyclerView(MainActivity.this,this);
        movieRecyclerView.setAdapter(movieAdapter);
        makeUrl(NetworkUtils.getBaseUrl());
    }

    @Override
    public void onListItemClick(Result movieDataFromAdapterForIntent) {
        Context parentClass=MainActivity.this;
        Class childClass=MovieDetailsAfterClick.class;
        Intent passDataToChildClass= new Intent(parentClass,childClass);
        passDataToChildClass.putExtra("mDataFromIntent",movieDataFromAdapterForIntent.getPosterPath());
        passDataToChildClass.putExtra("mDataFromIntent1",movieDataFromAdapterForIntent.getOriginalTitle());
        passDataToChildClass.putExtra("mDataFromIntent2",movieDataFromAdapterForIntent.getOverview());
        passDataToChildClass.putExtra("mDataFromIntent3",movieDataFromAdapterForIntent.getVoteAverage());
        passDataToChildClass.putExtra("mDataFromIntent4",movieDataFromAdapterForIntent.getReleaseDate());

        Log.d(TAG1,"THE data passing from the intent is ;;;;;;;;;;;;;;;;;;*********************"+movieDataFromAdapterForIntent.getPosterPath());
        Log.d(TAG1,"THE data passing from the intent is ;;;;;;;;;;;;;;;;;;*********************"+movieDataFromAdapterForIntent.getOriginalTitle());
        Log.d(TAG1,"THE data passing from the intent is ;;;;;;;;;;;;;;;;;;*********************"+movieDataFromAdapterForIntent.getOverview());
        Log.d(TAG1,"THE data passing from the intent is ;;;;;;;;;;;;;;;;;;*********************"+movieDataFromAdapterForIntent.getReleaseDate());
        Log.d(TAG1,"THE data passing from the intent is ;;;;;;;;;;;;;;;;;;*********************"+movieDataFromAdapterForIntent.getVoteAverage());
        startActivity(passDataToChildClass);

//        if(passDataToChildClass.resolveActivity(getPackageManager())!=null)
//        {
//            startActivity(passDataToChildClass);
//        }
    }

    public static class MovieTask extends AsyncTask<URL ,Void, List<Result>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Result> doInBackground(URL... urls)
        {
            List<Result> results=null;
            try{
                URL popularUrl = urls[0];
                String movieSearchResults = callHttp(popularUrl);
                results=Result.DataFromJSON(movieSearchResults);
                return results;
            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(List<Result> movieSearchReults)
        {
           spinner.setVisibility(View.INVISIBLE);

            if(movieSearchReults!=null)
            {
                movieAdapter.setData(movieSearchReults);
                movieAdapter.notifyDataSetChanged();
            }
            else
            {
                tv_showData.setVisibility(View.VISIBLE);
                tv_showData.setText(R.string.noInternetMessage);


                Log.d(TAG1, "the value in the tv_showData Text View is:" +tv_showData.getText());
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public static void makeUrl(String urlpass)
    {
            //String movieUrl = null;
            URL builtUrl = NetworkUtils.urlForPopular(urlpass);
            new MovieTask().execute(builtUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ItemOfMenu=item.getItemId();
        if(ItemOfMenu==R.id.mostPopular)
        {

            movieAdapter.setData(null);
            movieAdapter.notifyDataSetChanged();
            makeUrl(NetworkUtils.getPopularUrl());
            return true;


        }
        if (ItemOfMenu==R.id.topRated)
        {

            movieAdapter.setData(null);
            movieAdapter.notifyDataSetChanged();
            makeUrl(NetworkUtils.getTopRatedUrl());
            return true;
            }
        if(ItemOfMenu==R.id.refreshButton)
        {

            movieAdapter.setData(null);
            movieAdapter.notifyDataSetChanged();
            makeUrl(NetworkUtils.getBaseUrl());
            return true;
        }
        return super.onOptionsItemSelected(item);

}
        public static String callHttp(URL fullUrl)
        {
        String SearchResults = null;
        try {
        SearchResults = NetworkUtils.getResponseFromHttpUrl(fullUrl);

            } catch (IOException e) {
                e.printStackTrace();
                    }
                return SearchResults;

        }

}
