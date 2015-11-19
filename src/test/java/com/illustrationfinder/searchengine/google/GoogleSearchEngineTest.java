package com.illustrationfinder.searchengine.google;

import com.illustrationfinder.searchengine.ISearchEngine;
import com.illustrationfinder.searchengine.google.GoogleSearchEngine;
import com.illustrationfinder.searchengine.google.GoogleSearchParameters;
import com.illustrationfinder.searchengine.google.GoogleSearchResults;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by alombard on 19/11/2015.
 */
public class GoogleSearchEngineTest {
    @Test
    public void testSearch() throws IOException {
        final GoogleSearchParameters parameters = new GoogleSearchParameters();
        parameters.setKeywords("Tomato");

        final ISearchEngine<GoogleSearchParameters, GoogleSearchResults> searchEngine = new GoogleSearchEngine();
        final GoogleSearchResults results = searchEngine.search(parameters);


    }
}
