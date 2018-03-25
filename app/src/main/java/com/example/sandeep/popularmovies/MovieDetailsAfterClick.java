package com.example.sandeep.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsAfterClick extends AppCompatActivity {
    ImageView moviePoster;
    TextView movieName;
    TextView movieRatings;
    TextView movieReleaseDate;
    TextView movieSynopsis;
    TextView mLanguage;


    String posterPath=null;
    String movieTitile;
    String mReleaseDate;
    String movieOverview;
    String movieLanguage;
    String movieRating;
    private static final String TAG2 = MovieDetailsAfterClick.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_after_click);

        movieName=(TextView)findViewById(R.id.mName);
        moviePoster=(ImageView) findViewById(R.id.mPoster);
        movieRatings=(TextView)findViewById(R.id.mRating);
        movieReleaseDate=(TextView)findViewById(R.id.mReleaseDate);
        movieSynopsis=(TextView)findViewById(R.id.mSynopsis);
        mLanguage=(TextView)findViewById(R.id.mLanguage);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent datafromParent;
        datafromParent = getIntent();
        posterPath=datafromParent.getStringExtra("mDataFromIntent");
        movieTitile=datafromParent.getStringExtra("mDataFromIntent1");
        mReleaseDate=datafromParent.getStringExtra("mDataFromIntent4");
        movieOverview=datafromParent.getStringExtra("mDataFromIntent2");

        movieRating=datafromParent.getStringExtra("mDataFromIntent3");

  /*this is the passing by Result class onject
        Result jsonDataFromParentActivity=datafromParent.getParcelableExtra("mDataFromIntent");
        Log.d(TAG2,"the url of the poster from function call of child class"+posterPath);
        //posterPath=jsonDataFromParentActivity.getPosterPath();
        Log.d(TAG2,"the url of the poster of child class"+posterPath);
//        movieTitile=jsonDataFromParentActivity.getTitle();
//        mReleaseDate=jsonDataFromParentActivity.getReleaseDate();
//        movieOverview=jsonDataFromParentActivity.getOverview();
//        movieLanguage=jsonDataFromParentActivity.getOriginalLanguage();
//        movieRating=jsonDataFromParentActivity.getVoteAverage();*/
        Log.d(TAG2,"the Title :::::::::"+movieTitile);
        Log.d(TAG2,"the releaseDate :::::::::"+mReleaseDate);
        Log.d(TAG2,"the Overview :::::::::"+movieOverview);
        Log.d(TAG2,"the Rating :::::::::"+movieRating);

        Picasso.with(this).load(posterPath).into(moviePoster);
        movieName.setText(movieTitile);
        movieRatings.setText(movieRating);
        movieReleaseDate.append(mReleaseDate);
        movieSynopsis.setText(movieOverview);
        mLanguage.setText(movieLanguage);


    }



}
