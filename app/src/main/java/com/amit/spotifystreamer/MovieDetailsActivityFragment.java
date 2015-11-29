package com.amit.spotifystreamer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

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
        return view;
    }
}
