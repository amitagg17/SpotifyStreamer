package com.amit.spotifystreamer.com.amit.spotifystreamer.data;

import android.provider.BaseColumns;

/**
 * Created by amitkumaragarwal on 04/01/16.
 */
public class MovieContract {

    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        /*
          private String id;
    private String poster;
    private String originalTitle;
    private String synopsis;
    private double ratings;
    private String releaseDate;
         */
        public static final String MOVIE_ID = "movieId";
        public static final String POSTER = "poster";

    }
}
