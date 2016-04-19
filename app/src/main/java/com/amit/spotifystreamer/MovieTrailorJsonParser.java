package com.amit.spotifystreamer;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitkumaragarwal on 03/01/16.
 */
public class MovieTrailorJsonParser {

    public static List<MovieTrailor> parseMovieTrailors(String jsonStr) {

        ArrayList<MovieTrailor> moviesTrailors = new ArrayList<>();
        if (!TextUtils.isEmpty(jsonStr)) {
            try {
                JSONObject responseJson = new JSONObject(jsonStr);
                JSONArray resultList = responseJson.getJSONArray("results");
                for (int index = 0; index < resultList.length(); index++) {
                    JSONObject movieTrailorJson = resultList.getJSONObject(index);
                    String key = movieTrailorJson.getString("key");
                    String site = movieTrailorJson.getString("site");
                    String title = movieTrailorJson.getString("name");

                    MovieTrailor movieTrailor = new MovieTrailor();
                    movieTrailor.setTitle(title);
                    movieTrailor.setKey(key);
                    moviesTrailors.add(movieTrailor);
                }

            } catch (JSONException e) {

            }
        }

        return moviesTrailors;
    }
}
