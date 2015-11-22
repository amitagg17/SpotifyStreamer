package com.amit.spotifystreamer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitkumaragarwal on 22/11/15.
 */
public class MovieJsonParser {
    /*
    private String originalTitle;
    private String thubnailUrl;
    private String synopsis;
    private double ratings;
    private String releaseDate;
    */
    public static List<Movie> parseMovie(String jsonStr) {

        ArrayList<Movie> movies = new ArrayList<>();
        try {
            JSONObject responseJson = new JSONObject(jsonStr);
            JSONArray resultList = responseJson.getJSONArray("results");
            for (int index = 0; index < resultList.length(); index++) {
                JSONObject movieJson = resultList.getJSONObject(index);
                String posterUrl = movieJson.getString("poster_path");
                String title = movieJson.getString("original_title");
                String synopsis = movieJson.getString("overview");
                double ratings = movieJson.getDouble("vote_average");
                String releaseDate = movieJson.getString("release_date");

                Movie movie = new Movie();
                movie.setPoster(posterUrl);
                movie.setOriginalTitle(title);
                movie.setRatings(ratings);
                movie.setReleaseDate(releaseDate);
                movie.setSynopsis(synopsis);
                movies.add(movie);
            }

        }
        catch (JSONException e) {

        }

        return movies;
    }
}
