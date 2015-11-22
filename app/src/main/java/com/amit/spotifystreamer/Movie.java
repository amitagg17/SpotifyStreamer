package com.amit.spotifystreamer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amitkumaragarwal on 22/11/15.
 */
public class Movie implements Parcelable {
    private String poster;
    private String originalTitle;
    private String synopsis;
    private double ratings;
    private String releaseDate;

    public Movie() {

    }
    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster);
        dest.writeString(originalTitle);
        dest.writeString(synopsis);
        dest.writeDouble(ratings);
        dest.writeString(releaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        poster = in.readString();
        originalTitle = in.readString();
        synopsis = in.readString();
        ratings = in.readDouble();
        releaseDate = in.readString();
    }

}
