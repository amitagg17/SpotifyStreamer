package com.amit.spotifystreamer;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

    private static final String TAG = MovieDetailsActivityFragment.class.getSimpleName();

    @Bind(R.id.release_date) TextView releaseDate;
    @Bind(R.id.ratings) TextView ratings;
    @Bind(R.id.posterImageView) ImageView poster;
    @Bind(R.id.textViewSynopsis) TextView synopsis;
    @Bind(R.id.duration) TextView duration;

    public MovieDetailsActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);
        Movie movie = getActivity().getIntent().getParcelableExtra(MovieDetailsActivity.EXTRA_MOVIE);
        try {
            releaseDate.setText("" + movie.getReleaseDateYear());
        }
        catch (Exception e) {
            releaseDate.setVisibility(View.GONE);
        }
        ratings.setText("" + movie.getRatings() + "/10.0");
        String posterUrl = SpotifyStreamerConstants.getMoviePosterUrl(movie.getPoster(), SpotifyStreamerConstants.MoviePosterSize.W342);
        Picasso.with(getActivity()).load(posterUrl).into(poster);
        if (!TextUtils.isEmpty(movie.getSynopsis())) {
            synopsis.setText(movie.getSynopsis());
        }

        String trailerVideosUrl = SpotifyStreamerConstants.getMovieTrailerVideos(movie.getId());
        if (!TextUtils.isEmpty(trailerVideosUrl)) {
            FetchMovieTrailorAsyncTask fetchMovieTrailorAsyncTask = new FetchMovieTrailorAsyncTask(this);
            fetchMovieTrailorAsyncTask.execute(trailerVideosUrl);
        }
        String reviewUrl = SpotifyStreamerConstants.getMovieReview(movie.getId());
        if (!TextUtils.isEmpty(trailerVideosUrl)) {
            FetchMovieReviewsAsyncTask fetchMovieReviewAsyncTask = new FetchMovieReviewsAsyncTask(this);
            fetchMovieReviewAsyncTask.execute(reviewUrl);
        }
        return view;
    }

    private void loadMovieTrailors(List<MovieTrailor> list) {
        MovieDetailRecycleViewAdapter movieDetailRecycleViewAdapter = new MovieDetailRecycleViewAdapter(list);

    }

    private static class FetchMovieTrailorAsyncTask extends AsyncTask<String, Void, String> {

        private WeakReference<MovieDetailsActivityFragment> mMainActivityFragment;
        public FetchMovieTrailorAsyncTask(MovieDetailsActivityFragment fragment) {
            mMainActivityFragment = new WeakReference<MovieDetailsActivityFragment>(fragment);
        }
        @Override
        protected String doInBackground(String... params) {
            String response = NetworkingHelper.sendRequest(params[0]);
            Log.i(TAG, "response = " + response);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<MovieTrailor> movieTrailors = MovieTrailorJsonParser.parseMovieTrailors(s);


            MovieDetailsActivityFragment fragment = mMainActivityFragment.get();
        }
    }

    private static class FetchMovieReviewsAsyncTask extends AsyncTask<String, Void, String> {

        private WeakReference<MovieDetailsActivityFragment> mMainActivityFragment;
        public FetchMovieReviewsAsyncTask(MovieDetailsActivityFragment fragment) {
            mMainActivityFragment = new WeakReference<MovieDetailsActivityFragment>(fragment);
        }
        @Override
        protected String doInBackground(String... params) {
            String response = NetworkingHelper.sendRequest(params[0]);
            Log.i(TAG, "response = " + response);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<Movie> movies = MovieJsonParser.parseMovie(s);


            MovieDetailsActivityFragment fragment = mMainActivityFragment.get();
        }
    }

    private static class MovieReview {

    }

    private class MovieDetailRecycleViewAdapter extends RecyclerView.Adapter<MovieDetailRecycleViewAdapter.MovieViewHolder> {
        public class MovieViewHolder extends RecyclerView.ViewHolder {
            public MovieViewHolder(View view) {
                super(view);
            }
        }
        private List<MovieTrailor> list;
        public MovieDetailRecycleViewAdapter(List<MovieTrailor> movieTrailors) {
            list = movieTrailors;
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
