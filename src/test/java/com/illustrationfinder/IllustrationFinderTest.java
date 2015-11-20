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


import com.illustrationfinder.process.image.BufferedImageProcessor;
import com.illustrationfinder.process.image.IImageProcessor;
import com.illustrationfinder.process.post.IPostProcessor;
import com.illustrationfinder.process.post.HtmlPostProcessor;
import com.illustrationfinder.process.searchengine.google.GoogleSearchEngine;
import com.illustrationfinder.process.searchengine.google.GoogleSearchParameters;
import com.illustrationfinder.process.searchengine.google.GoogleSearchResults;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *
 */
public class IllustrationFinderTest {
    public void testIllustrationFinder() throws IOException {
        final IPostProcessor postProcessor = new HtmlPostProcessor();
        final GoogleSearchEngine searchEngine = new GoogleSearchEngine();
        final IImageProcessor<BufferedImage, BufferedImageOp> imageProcessor = new BufferedImageProcessor();

        final IllustrationFinder illustrationFinder = new IllustrationFinder();
        illustrationFinder.setPostProcessor(postProcessor);
        illustrationFinder.setSearchEngine(searchEngine);
        illustrationFinder.setImageProcessor(imageProcessor);

        final List<BufferedImage> images = illustrationFinder.getImages(new URL("http://www.hankerspace.com/fr/injection-de-code-a-froid-dans-un-executable-windows/"));


    }
}
