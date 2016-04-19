package com.amit.spotifystreamer;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by amitkumaragarwal on 02/01/16.
 */
public class MovieFavorites {

    private final ArrayList<String> favoriteMovies = new ArrayList<>();
    private Context mContext;
    private static MovieFavorites instance;

    private MovieFavorites(Context context) {
        mContext = context;
    }
    public static MovieFavorites getInstance(Context context) {
        if (instance == null) {
            instance = new MovieFavorites(context.getApplicationContext());
        }
        return instance;
    }

    public void markFavorite(String movieId) {
        favoriteMovies.add(movieId);
    }

    public void unmarkFavorite(String movieId) {
        favoriteMovies.remove(movieId);
    }

    public ArrayList<String> getFavoriteMovies() {
        return new ArrayList<>(favoriteMovies);
    }


    private void loadFavorites() {

    }

    private void saveFavorites() {

    }

}
