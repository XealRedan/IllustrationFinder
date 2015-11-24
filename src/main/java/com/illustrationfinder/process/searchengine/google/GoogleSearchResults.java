package com.illustrationfinder.process.searchengine.google;

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

        for(Result r : this.responseData.getResults()) {
            results.add(r.getUrl());
        }

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
