package com.amit.spotifystreamer;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private MoviePosterAdapter mMovieAdapter;
    private MovieSelectListener movieSelectListener;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void loadMovies(List<Movie> movies) {
        mMovieAdapter.setmMovies(movies);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String url = null;
        if (id == R.id.action_sort_by_popularity) {
            url = SpotifyStreamerConstants.getPopularMovieUrl();
        } else if (id == R.id.action_sort_by_ratings) {
            url = SpotifyStreamerConstants.getHighestRatedMovieUrl();
        }
        if (!TextUtils.isEmpty(url)) {
            FetchPopularMovieAsyncTask fetchPopularMovieAsyncTask = new FetchPopularMovieAsyncTask(this);
            fetchPopularMovieAsyncTask.execute(url);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.spotify_streamer, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        FetchPopularMovieAsyncTask fetchPopularMovieAsyncTask = new FetchPopularMovieAsyncTask(this);
        fetchPopularMovieAsyncTask.execute(SpotifyStreamerConstants.getPopularMovieUrl());
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        mMovieAdapter = new MoviePosterAdapter(getActivity());
        gridView.setAdapter(mMovieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie)mMovieAdapter.getItem(position);
                if (movieSelectListener != null) {
                    movieSelectListener.onMovieSelected(movie);
                }
            }
        });
        if (getActivity() != null && getActivity() instanceof MovieSelectListener) {
            movieSelectListener = (MovieSelectListener)getActivity();
        }
        return view;
    }

    private static class FetchPopularMovieAsyncTask extends AsyncTask<String, Void, String> {

        private WeakReference<MainActivityFragment> mMainActivityFragment;
        public FetchPopularMovieAsyncTask(MainActivityFragment fragment) {
            mMainActivityFragment = new WeakReference<MainActivityFragment>(fragment);
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


            MainActivityFragment fragment = mMainActivityFragment.get();
            if (fragment != null) {
                fragment.loadMovies(movies);
            }
        }
    }

    private static class MoviePosterAdapter extends BaseAdapter {
        private ArrayList<Movie> mMovies;
        private LayoutInflater mInflator;
        private Context mContext;

        public MoviePosterAdapter(Context context) {
            mMovies = new ArrayList<Movie>();
            mInflator = LayoutInflater.from(context);
            mContext = context.getApplicationContext();
        }

        public void setmMovies(List<Movie> movies) {
            mMovies = new ArrayList<Movie>(movies);;
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return mMovies.size();
        }

        @Override
        public Object getItem(int position) {
            return mMovies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflator.inflate(R.layout.movie_poster_view, null);
            }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.poster_image);
            String moviePoster = SpotifyStreamerConstants.getMoviePosterUrl(mMovies.get(position).getPoster(), SpotifyStreamerConstants.MoviePosterSize.W342);
            Picasso.with(mContext).load(moviePoster).into(imageView);
            return convertView;
        }
    }

    public interface MovieSelectListener {
        public void onMovieSelected(Movie movie);
    }
}
