package com.illustrationfinder;

import com.illustrationfinder.process.image.IImageProcessor;
import com.illustrationfinder.process.paper.IPostProcessor;
import com.illustrationfinder.process.searchengine.ISearchEngine;
import com.illustrationfinder.process.searchengine.google.GoogleSearchParameters;
import com.illustrationfinder.process.searchengine.google.GoogleSearchResults;

import java.awt.image.BufferedImage;

/**
 * Created by Alexandre on 19/11/2015.
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
