package com.amit.spotifystreamer;

import java.util.HashMap;

/**
 * Created by amitkumaragarwal on 15/11/15.
 */
public class SpotifyStreamerConstants {

    public static final String MOVIE_META_DATA_INFO_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";

    public enum MoviePosterSize {
        ORIGINAL,
        W92,
        W154,
        W185,
        W342,
        W500,
        W780
    }

    public static final String API_KEY = "3afabe94d308de1c7e8d90c3d504aa25";
    private static HashMap<MoviePosterSize, String> moviePosterSizeMap = new HashMap<MoviePosterSize, String>();

    static {
        moviePosterSizeMap.put(MoviePosterSize.ORIGINAL, "original");
        moviePosterSizeMap.put(MoviePosterSize.W92, "W92");
        moviePosterSizeMap.put(MoviePosterSize.W154, "W154");
        moviePosterSizeMap.put(MoviePosterSize.W185, "W185");
        moviePosterSizeMap.put(MoviePosterSize.W342, "W342");
        moviePosterSizeMap.put(MoviePosterSize.W500, "W500");
        moviePosterSizeMap.put(MoviePosterSize.W780, "W780");

    }

    private static final String MOVIE_POSTER_DOWNLOAD_BASE_URL = "http://image.tmdb.org/t/p";


    // "w92", "w154", "w185", "w342", "w500", "w780", or "original"

    public static String getMoviePosterDownloadBaseUrl() {
        return MOVIE_POSTER_DOWNLOAD_BASE_URL;
    }

    public static String getMoviePosterDefaultSizeUrl(String posterRelativeUrl) {
        return getMoviePosterUrl(posterRelativeUrl, MoviePosterSize.W185);
    }

    public static String getMoviePosterUrl(String posterRelativeUrl, MoviePosterSize size) {
        StringBuilder sb = new StringBuilder();
        sb.append(MOVIE_POSTER_DOWNLOAD_BASE_URL);
        sb.append("/");
        sb.append(getMovieWidthString(size));
        sb.append("/");
        sb.append(posterRelativeUrl);
        return sb.toString();
    }

    private static String getMovieWidthString(MoviePosterSize posterSize) {
        return moviePosterSizeMap.get(posterSize);
    }

}
