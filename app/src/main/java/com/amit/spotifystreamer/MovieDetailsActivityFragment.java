package com.amit.spotifystreamer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

    @Bind(R.id.textViewMovieTitle) TextView title;
    @Bind(R.id.release_date) TextView releaseDate;
    @Bind(R.id.ratings) TextView ratings;
    @Bind(R.id.posterImageView) ImageView poster;
    @Bind(R.id.textViewSynopsis) TextView synopsis;

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
        title.setText(movie.getOriginalTitle());
        releaseDate.setText(movie.getReleaseDate());
        ratings.setText("" + movie.getRatings() + "/10");
        String posterUrl = SpotifyStreamerConstants.getMoviePosterDefaultSizeUrl(movie.getPoster());
        Picasso.with(getActivity()).load(posterUrl).into(poster);
        synopsis.setText(movie.getSynopsis());
        return view;
    }
}
