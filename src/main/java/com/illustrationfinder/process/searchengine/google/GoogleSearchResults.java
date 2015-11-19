package com.illustrationfinder.process.searchengine.google;

import com.illustrationfinder.process.searchengine.ISearchEngineResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alombard on 19/11/2015.
 */
public class GoogleSearchResults implements ISearchEngineResults {
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    @Override
    public List<String> getResults() {
        if(this.responseData == null)
            return null;

        final List<String> results = new ArrayList<>();

        this.responseData.getResults().forEach(r -> results.add(r.getUrl()));

        return results;
    }

    @Override
    public String toString() {
        return "ResponseData[" + responseData + "]";
    }

    public static class ResponseData {
        private List<Result> results;

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        public String toString() {
            return "Results[" + results + "]";
        }
    }

    public static class Result {
        private String url;
        private String title;

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String toString() {
            return "Result[url:" + url +",title:" + title + "]";
        }
    }
}
