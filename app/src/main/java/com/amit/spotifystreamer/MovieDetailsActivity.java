package com.amit.spotifystreamer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Movie movie = getIntent().getParcelableExtra(MovieDetailsActivity.EXTRA_MOVIE);
        setTitle(movie.getOriginalTitle());
    }
}
