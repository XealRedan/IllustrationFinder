package com.illustrationfinder.searchengine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Created by alombard on 19/11/2015.
 */
public interface ISearchEngine<Parameters, Result> {
    Result search(Parameters parameters) throws IOException;
}
