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
import com.illustrationfinder.process.post.HtmlPostProcessor;
import com.illustrationfinder.process.post.IPostProcessor;
import com.illustrationfinder.process.searchengine.google.GoogleSearchEngine;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class IllustrationFinderTest {
//    @Test
    public void testIllustrationFinder() throws IOException {
        final IPostProcessor postProcessor = new HtmlPostProcessor();
        final GoogleSearchEngine searchEngine = new GoogleSearchEngine();
        final IImageProcessor<BufferedImage, BufferedImageOp> imageProcessor = new BufferedImageProcessor();

        imageProcessor.setPreferredSize(new Dimension(512, 512));

        final IllustrationFinder illustrationFinder = new IllustrationFinder();
        illustrationFinder.setPostProcessor(postProcessor);
        illustrationFinder.setSearchEngine(searchEngine);
        illustrationFinder.setImageProcessor(imageProcessor);

        final List<String> articles = new ArrayList<>();

        articles.add("http://www.hankerspace.com/fr/injection-de-code-a-froid-dans-un-executable-windows/");
        articles.add("http://www.hankerspace.com/fr/creer-un-trigger-bot-pour-counter-strike/");
        articles.add("http://www.hankerspace.com/fr/javascript-la-verite-sur-les-operateurs-et/");
        articles.add("http://www.hankerspace.com/fr/keylogger-et-mouselogger-en-c/");
        articles.add("http://www.hankerspace.com/fr/windows-hooks-et-dlls/");
        articles.add("http://www.hankerspace.com/fr/presentation-de-la-bibliotheque-blackbone/");

        int articleIndex = 0;
        for(String article : articles) {
            final List<BufferedImage> images = illustrationFinder.getImages(new URL(article));

            if(!new File("out/article_" + articleIndex).exists()) {
                new File("out/article_" + articleIndex).mkdir();
            }

            int idx = 0;
            for(BufferedImage img : images) {

                ImageIO.write(img, "PNG", new File("out/article_" + articleIndex + "/image_" + idx + ".png"));
                idx++;
            }

            articleIndex++;
        }


    }
}
