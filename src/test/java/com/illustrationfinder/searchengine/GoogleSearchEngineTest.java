package com.illustrationfinder.searchengine;

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
