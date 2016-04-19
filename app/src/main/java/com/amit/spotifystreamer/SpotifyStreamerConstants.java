package com.amit.spotifystreamer;

import android.net.Uri;

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
        moviePosterSizeMap.put(MoviePosterSize.W92, "w92");
        moviePosterSizeMap.put(MoviePosterSize.W154, "w154");
        moviePosterSizeMap.put(MoviePosterSize.W185, "w185");
        moviePosterSizeMap.put(MoviePosterSize.W342, "w342");
        moviePosterSizeMap.put(MoviePosterSize.W500, "w500");
        moviePosterSizeMap.put(MoviePosterSize.W780, "w780");

    }

    private static final String MOVIE_POSTER_DOWNLOAD_BASE_URL = "http://image.tmdb.org/t/p";
    private static final String MOVIE_END_POINT_URL = "http://api.themoviedb.org/3/discover/movie";
    private static final String MOVIE_API_FILTER_PARAM_KEY = "sort_by";
    private static final String MOVIE_API_POPULAR_MOVIE_FILER = "popularity.desc";
    private static final String MOVIE_API_HIGHEST_RATER_FILER = "vote_average.desc";
    private static final String MOVIE_API_KEY = "api_key";

    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie";
    private static final String MOVIE_TRAILOR_VIDEOS = "videos";
    private static final String MOVIE_REVIEWS = "reviews";

    public static String getMoviePosterDownloadBaseUrl() {
        return MOVIE_POSTER_DOWNLOAD_BASE_URL;
    }

    public static String getMoviePosterDefaultSizeUrl(String posterRelativeUrl) {
        return getMoviePosterUrl(posterRelativeUrl, MoviePosterSize.W185);
    }

    public static String getPopularMovieUrl() {

        Uri builtUri = Uri.parse(MOVIE_END_POINT_URL).buildUpon()
                .appendQueryParameter(MOVIE_API_FILTER_PARAM_KEY, MOVIE_API_POPULAR_MOVIE_FILER)
                .appendQueryParameter(MOVIE_API_KEY, API_KEY)
                .build();
        return builtUri.toString();

    }

    public static String getHighestRatedMovieUrl() {

        Uri builtUri = Uri.parse(MOVIE_END_POINT_URL).buildUpon()
                .appendQueryParameter(MOVIE_API_FILTER_PARAM_KEY, MOVIE_API_HIGHEST_RATER_FILER)
                .appendQueryParameter(MOVIE_API_KEY, API_KEY)
                .build();
        return builtUri.toString();

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

    public static String getMovieTrailerVideos(String movieId) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendPath(MOVIE_TRAILOR_VIDEOS)
                .appendQueryParameter(MOVIE_API_KEY, API_KEY)
                .build();
        return builtUri.toString();
    }

    public static String getMovieReview(String movieId) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendPath(MOVIE_REVIEWS)
                .appendQueryParameter(MOVIE_API_KEY, API_KEY)
                .build();
        return builtUri.toString();
    }

}
