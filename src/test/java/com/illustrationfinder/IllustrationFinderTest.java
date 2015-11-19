package com.illustrationfinder;

/*
 * #%L
 * IllustrationFinder
 * %%
 * Copyright (C) 2015 Alexandre Lombard
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.illustrationfinder.process.post.IPostProcessor;
import com.illustrationfinder.process.post.HtmlPostProcessor;
import com.illustrationfinder.process.searchengine.google.GoogleSearchEngine;
import com.illustrationfinder.process.searchengine.google.GoogleSearchParameters;
import com.illustrationfinder.process.searchengine.google.GoogleSearchResults;

import java.io.IOException;

/**
 *
 */
public class IllustrationFinderTest {
    public void testIllustrationFinder() throws IOException {
        final IPostProcessor postProcessor = new HtmlPostProcessor();
        final String keywords = postProcessor.generateKeywords();

        final GoogleSearchEngine searchEngine = new GoogleSearchEngine();
        final GoogleSearchParameters searchParameters = new GoogleSearchParameters();

        searchParameters.setDomain(GoogleSearchParameters.Domain.IMAGES);
        searchParameters.setKeywords("Tomato");

        final GoogleSearchResults searchResults = searchEngine.search(searchParameters);

        final IllustrationFinder illustrationFinder = new IllustrationFinder();
        illustrationFinder.setPostProcessor(postProcessor);
        illustrationFinder.setSearchEngine(searchEngine);


    }
}
