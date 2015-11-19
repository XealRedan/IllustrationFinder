package com.illustrationfinder.process.post;

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


import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.net.URL;
import java.security.Key;

/**
 * Post processor for HTML posts
 */
public class HtmlPostProcessor implements IPostProcessor {

    private URL url;

    public HtmlPostProcessor() {
        //
    }

    /**
     * Build a HtmlPostProcessor for the given url
     * @param url the post URL
     */
    public HtmlPostProcessor(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String generateKeywords() {
        if(this.url == null)
            return null;

        try {
            // Retrieve the document and save it temporary
            final String articleText = ArticleExtractor.getInstance().getText(this.url);

            final KeywordExtractor keywordExtractor = new KeywordExtractor();

            return keywordExtractor.getKeywords(articleText);
        } catch (BoilerpipeProcessingException e) {
            // TODO
            e.printStackTrace();
        }

        return null;
    }
}
