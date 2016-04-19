package com.amit.spotifystreamer;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by amitkumaragarwal on 22/11/15.
 */
public class Movie implements Parcelable {
    private String id;
    private String poster;
    private String originalTitle;
    private String synopsis;
    private double ratings;
    private String releaseDate;

    private static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    public Movie() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public int getReleaseDateYear() {
        SimpleDateFormat sdf = new SimpleDateFormat(RELEASE_DATE_FORMAT);
        ParsePosition parsePosition = new ParsePosition(0);
        final Calendar calendar = Calendar.getInstance();
        Date date = sdf.parse(releaseDate, parsePosition);
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
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
        dest.writeString(id);
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
        id = in.readString();
        poster = in.readString();
        originalTitle = in.readString();
        synopsis = in.readString();
        ratings = in.readDouble();
        releaseDate = in.readString();
    }

}
