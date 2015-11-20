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
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.security.Key;
import java.util.*;

/**
 * Post processor for HTML posts
 */
public class HtmlPostProcessor implements IPostProcessor {

    private static final String PUNCTUATION_REGEX = "[\\.\\?!\\+\\-,;:/\\\\=_|§]+";
    private static final String WORD_WITH_LESS_THAN_4_CHARACTERS_REGEX = "\\b[\\w']{1,4}\\b";
    private static final String EXCESSIVE_SPACING_REGEX = "\\s{2,}";

    private static final Integer MINIMUM_KEYWORDS_COUNT = 4;

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

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public List<String> generateKeywords() {
        if(this.url == null)
            return null;

        try {
            // Retrieve the document and store it temporary
            final String rawText = IOUtils.toString(this.url.openStream());

            // Retrieve useful HTML data
            final Document document = Jsoup.parse(rawText);

            String htmlTitle = document.title();
            String htmlKeywords = document.select("meta[name=keywords]").text();
            String htmlDescription = document.select("meta[name=description]").text();

            // Extract the content of the raw text
            String content = ArticleExtractor.getInstance().getText(rawText);

            // Now we apply a simple algorithm to get keywords
            //  1) We remove all punctuation marks from the title
            //  2) We remove all words with less than 4 characters
            //  3) We remove excessive spacing and tabulations

            htmlTitle = htmlTitle.toLowerCase();
            htmlTitle = htmlTitle.replaceAll(PUNCTUATION_REGEX, "");
            htmlTitle = htmlTitle.replaceAll(WORD_WITH_LESS_THAN_4_CHARACTERS_REGEX, "");
            htmlTitle = htmlTitle.replaceAll(EXCESSIVE_SPACING_REGEX, " ");

            final List<String> keywords = new ArrayList<>();
            keywords.addAll(Arrays.asList(htmlTitle.split(" ")));

            // If there is enough keywords, we return
            if(keywords.size() >= MINIMUM_KEYWORDS_COUNT) {
                return keywords;
            } else {
                // Otherwise, we look for more keywords from the text by taking the more frequent words
                content = content.toLowerCase();
                content = content.replaceAll(PUNCTUATION_REGEX, "");
                content = content.replaceAll(WORD_WITH_LESS_THAN_4_CHARACTERS_REGEX, "");
                content = content.replaceAll(EXCESSIVE_SPACING_REGEX, " ");

                final Map<String, Integer> frequencies = new HashMap<>();
                final String[] words = content.split(" ");

                // Count word frequencies
                for(final String word : words) {
                    if(frequencies.containsKey(word)) {
                        frequencies.put(word, frequencies.get(word) + 1);
                    } else {
                        frequencies.put(word, 1);
                    }
                }

                // Sort the words per frequency
                final SortedMap<Integer, HashSet<String>> sortedWords = new TreeMap<>();

                for(Map.Entry<String, Integer> entry : frequencies.entrySet()) {
                    if(sortedWords.containsKey(entry.getValue())) {
                        sortedWords.get(entry.getValue()).add(entry.getKey());
                    } else {
                        final HashSet<String> set = new HashSet<>();
                        set.add(entry.getKey());
                        sortedWords.put(entry.getValue(), set);
                    }
                }

                // Add the most frequent words until we reach the minimu keywords count
                while(keywords.size() < MINIMUM_KEYWORDS_COUNT) {
                    final HashSet<String> set = sortedWords.get(sortedWords.lastKey());
                    final String keyword = set.iterator().next();

                    set.remove(keyword);
                    if(set.size() == 0) {
                        sortedWords.remove(sortedWords.lastKey());
                    }

                    keywords.add(keyword);
                }

                return keywords;
            }
        } catch (BoilerpipeProcessingException e) {
            // TODO
            e.printStackTrace();
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return null;
    }
}
