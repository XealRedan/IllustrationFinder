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


import com.entopix.maui.filters.MauiFilter;
import com.entopix.maui.main.MauiModelBuilder;
import com.entopix.maui.main.MauiTopicExtractor;
import com.entopix.maui.util.MauiDocument;
import com.entopix.maui.util.MauiTopics;
import com.entopix.maui.util.Topic;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Post processor for HTML posts
 */
public class HtmlPostProcessor implements IPostProcessor {

    private MauiModelBuilder modelBuilder = new MauiModelBuilder();
    private MauiTopicExtractor topicExtractor = new MauiTopicExtractor();

    private URL url;

    public HtmlPostProcessor() {

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
            final String articleText = ArticleExtractor.getInstance().getText(this.url);
            final List<MauiDocument> documentList = new ArrayList<>();

            documentList.add(new MauiDocument(this.url.getFile(), this.url.getPath(), articleText, ""));

            final MauiFilter model = this.modelBuilder.buildModel();
            this.topicExtractor.setModel(model);
            final List<MauiTopics> topics = this.topicExtractor.extractTopics(documentList);

            final StringBuilder keywordsStringBuilder = new StringBuilder();

            for(MauiTopics mauiTopic : topics) {
                for(Topic topic : mauiTopic.getTopics()) {
                    keywordsStringBuilder.append(topic.getTitle() + " ");
                }
            }

            return keywordsStringBuilder.toString().trim();
        } catch (BoilerpipeProcessingException e) {
            // TODO
            e.printStackTrace();
        } catch (MauiFilter.MauiFilterException e) {
            // TODO
            e.printStackTrace();
        }

        return null;
    }
}
