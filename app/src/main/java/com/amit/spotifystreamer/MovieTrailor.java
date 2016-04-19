package com.amit.spotifystreamer;

import android.net.Uri;

/**
 * Created by amitkumaragarwal on 03/01/16.
 */
class MovieTrailor {
    private static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch";

    private String title;
    private String site;
    private String key;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTrailorUrl() {
        Uri builtUri = Uri.parse(YOUTUBE_BASE_URL).buildUpon()
                .appendQueryParameter("v", key)
                .build();
        return builtUri.toString();
    }
}
