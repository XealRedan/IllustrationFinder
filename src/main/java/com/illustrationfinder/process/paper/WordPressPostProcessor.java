package com.illustrationfinder.process.paper;

import java.net.URL;

/**
 * Created by alombard on 19/11/2015.
 * Paper processor for WordPress articles
 */
public class WordPressPostProcessor implements IPostProcessor {

    private URL url;

    public WordPressPostProcessor() {

    }

    /**
     * Build a WordPressPostProcessor for the given url
     * @param url the paper URL
     */
    public WordPressPostProcessor(URL url) {
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
        return null;
    }
}
