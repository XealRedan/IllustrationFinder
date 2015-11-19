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


import com.illustrationfinder.process.image.IImageProcessor;
import com.illustrationfinder.process.post.IPostProcessor;
import com.illustrationfinder.process.searchengine.ISearchEngine;
import com.illustrationfinder.process.searchengine.google.GoogleSearchParameters;
import com.illustrationfinder.process.searchengine.google.GoogleSearchResults;

import java.awt.image.BufferedImage;

/**
 *
 */
public class IllustrationFinder {
    private IPostProcessor postProcessor;
    private ISearchEngine<GoogleSearchParameters, GoogleSearchResults> searchEngine;
    private IImageProcessor<BufferedImage> imageProcessor;

    public IllustrationFinder() {
        //
    }

    public IPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(IPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public ISearchEngine<GoogleSearchParameters, GoogleSearchResults> getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(ISearchEngine<GoogleSearchParameters, GoogleSearchResults> searchEngine) {
        this.searchEngine = searchEngine;
    }

    public IImageProcessor<BufferedImage> getImageProcessor() {
        return imageProcessor;
    }

    public void setImageProcessor(IImageProcessor<BufferedImage> imageProcessor) {
        this.imageProcessor = imageProcessor;
    }
}
