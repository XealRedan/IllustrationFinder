package com.illustrationfinder.process.searchengine.google;

import com.google.gson.Gson;
import com.illustrationfinder.process.searchengine.ISearchEngine;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by alombard on 19/11/2015.
 */
public class GoogleSearchEngine implements ISearchEngine<GoogleSearchParameters, GoogleSearchResults> {
    private static final String GOOGLE_WEB_URL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    private static final String GOOGLE_IMAGES_URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
    private static final String CHARSET = "UTF-8";

    @Override
    public GoogleSearchResults search(GoogleSearchParameters googleSearchParameters) throws IOException {
        final URL url;

        // Find the good url according to the search domain
        if(googleSearchParameters.getDomain() == GoogleSearchParameters.Domain.IMAGES)
            url = new URL(GOOGLE_IMAGES_URL + URLEncoder.encode(googleSearchParameters.getKeywords(), CHARSET));
        else
            url = new URL(GOOGLE_WEB_URL + URLEncoder.encode(googleSearchParameters.getKeywords(), CHARSET));

        // Read the results and build the object from the JSON data returned
        final Reader reader = new InputStreamReader(url.openStream(), CHARSET);
        final GoogleSearchResults results = new Gson().fromJson(reader, GoogleSearchResults.class);

        return results;
    }

    @Override
    public String toString() {
        return "Google Search Engine";
    }
}
