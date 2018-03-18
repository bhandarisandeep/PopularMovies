package com.example.sandeep.popularmovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(this, "You clicked most popular", Toast.LENGTH_SHORT).show();
        }
        if (ItemOfMenu==R.id.topRated)
        {
            Context context=MainActivity.this;
            Toast.makeText(this, "you clicked top rated", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);

}}
