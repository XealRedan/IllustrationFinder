package com.illustrationfinder.process.searchengine.google;

/**
 * Created by alombard on 19/11/2015.
 */
public class GoogleSearchParameters {
    private String keywords;
    private Domain domain = Domain.WEB;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return keywords;
    }

    public enum Domain {
        WEB,
        IMAGES
    }
}
