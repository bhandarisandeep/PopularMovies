package com.example.sandeep.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    TextView tv_showData;
  public static   ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       columns=2;
        // columns = getResources().getInteger(R.integer.movie_column);
    movieRecyclerView=(RecyclerView)findViewById(R.id.rv_movie_detail);
    tv_showData=(TextView)findViewById(R.id.tv_showData);
    spinner=(ProgressBar) findViewById(R.id.spinner);

        layoutForMovie=new GridLayoutManager(this,columns, LinearLayoutManager.VERTICAL,false);
        movieRecyclerView.setLayoutManager(layoutForMovie);
        movieRecyclerView.setHasFixedSize(true);
       // movieAdapter=new AdapterForRecyclerView(this);
        movieAdapter=new AdapterForRecyclerView(MainActivity.this);
        movieRecyclerView.setAdapter(movieAdapter);
        makePopularUrl();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

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
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public static void makePopularUrl()
    {
            //String movieUrl = null;
            URL builtUrl = NetworkUtils.urlForPopular();
            new MovieTask().execute(builtUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ItemOfMenu=item.getItemId();
        if(ItemOfMenu==R.id.mostPopular)
        {
            //Context context=MainActivity.this;
            //Toast.makeText(this, "You clicked most popular", Toast.LENGTH_SHORT).show();

        }
        if (ItemOfMenu==R.id.topRated)
        {
            Context context=MainActivity.this;
            Toast.makeText(this, "you clicked top rated", Toast.LENGTH_SHORT).show();
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
