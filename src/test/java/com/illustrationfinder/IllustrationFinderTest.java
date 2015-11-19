package com.illustrationfinder;

import com.illustrationfinder.process.paper.IPostProcessor;
import com.illustrationfinder.process.paper.WordPressPostProcessor;
import com.illustrationfinder.process.searchengine.ISearchEngine;
import com.illustrationfinder.process.searchengine.google.GoogleSearchEngine;
import com.illustrationfinder.process.searchengine.google.GoogleSearchParameters;
import com.illustrationfinder.process.searchengine.google.GoogleSearchResults;

import java.io.IOException;

/**
 * Created by Alexandre on 19/11/2015.
 */
public class IllustrationFinderTest {
    public void testIllustrationFinder() throws IOException {
        final IPostProcessor postProcessor = new WordPressPostProcessor();
        final String keywords = postProcessor.generateKeywords();

        final GoogleSearchEngine searchEngine = new GoogleSearchEngine();
        final GoogleSearchParameters searchParameters = new GoogleSearchParameters();

        searchParameters.setDomain(GoogleSearchParameters.Domain.IMAGES);
        searchParameters.setKeywords("Tomato");

        final GoogleSearchResults searchResults = searchEngine.search(searchParameters);

        for(final String result : searchResults.getResults()) {

        }
    }
}
