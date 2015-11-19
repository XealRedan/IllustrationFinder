package com.illustrationfinder.process.searchengine;

import java.io.IOException;

/**
 * Created by alombard on 19/11/2015.
 */
public interface ISearchEngine<Parameters, Result extends ISearchEngineResults> {
    Result search(Parameters parameters) throws IOException;
}
