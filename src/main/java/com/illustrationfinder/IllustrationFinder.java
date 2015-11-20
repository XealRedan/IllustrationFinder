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
import com.jhlabs.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class IllustrationFinder {
    private IPostProcessor postProcessor;
    private ISearchEngine<GoogleSearchParameters, GoogleSearchResults> searchEngine;
    private IImageProcessor<BufferedImage, BufferedImageOp> imageProcessor;

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

    public IImageProcessor<BufferedImage, BufferedImageOp> getImageProcessor() {
        return imageProcessor;
    }

    public void setImageProcessor(IImageProcessor<BufferedImage, BufferedImageOp> imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    public List<BufferedImage> getImages(URL url) throws IOException {
        // Extract the keywords
        this.postProcessor.setUrl(url);
        final List<String> keywords = this.postProcessor.generateKeywords();

        // Search for them on the search engine
        final GoogleSearchParameters parameters = new GoogleSearchParameters();
        final List<GoogleSearchResults> results = new ArrayList<>();
        for(int idx = 0; idx < 4; idx++) {
            parameters.setKeywords(keywords.get(idx));
            parameters.setDomain(GoogleSearchParameters.Domain.IMAGES);
            results.add(this.searchEngine.search(parameters));
        }

        // Extract 4 images
        final List<BufferedImage> sourceImages = new ArrayList<>();
        for(int idx = 0; idx < 4; idx++) {
            final BufferedImage image = ImageIO.read(new URL(results.get(idx).getResults().get(0)));

            sourceImages.add(this.imageProcessor.process(image));
        }

        return sourceImages;
    }

    public List<BufferedImage> getProcessedImages(BufferedImage source) {
        final List<BufferedImage> images = new ArrayList<>();

        this.imageProcessor.setAutomaticFilter(false);

        this.imageProcessor.setFilter(new ContrastFilter());
        images.add(this.imageProcessor.process(source));

        this.imageProcessor.setFilter(new KaleidoscopeFilter());
        images.add(this.imageProcessor.process(source));

        this.imageProcessor.setFilter(new DiffuseFilter());
        images.add(this.imageProcessor.process(source));

        this.imageProcessor.setFilter(new PinchFilter());
        images.add(this.imageProcessor.process(source));

        this.imageProcessor.setFilter(new InvertFilter());
        images.add(this.imageProcessor.process(source));

        return images;
    }
}
