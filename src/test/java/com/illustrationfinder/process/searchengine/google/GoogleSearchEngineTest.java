package com.illustrationfinder.process.searchengine.google;

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


import com.illustrationfinder.process.searchengine.ISearchEngine;
import org.junit.Test;

import java.io.IOException;

/**
 *
 */
public class GoogleSearchEngineTest {
//    @Test
    public void testSearch() throws IOException {
        final GoogleSearchParameters parameters = new GoogleSearchParameters();
        parameters.setKeywords("Tomato");
        parameters.setDomain(GoogleSearchParameters.Domain.IMAGES);

        final ISearchEngine<GoogleSearchParameters, GoogleSearchResults> searchEngine = new GoogleSearchEngine();
        final GoogleSearchResults results = searchEngine.search(parameters);

        for(GoogleSearchResults.Result result : results.getResponseData().getResults()) {
            System.out.println(result.getTitle());
            System.out.println(result.getUrl());
        }
    }
}
