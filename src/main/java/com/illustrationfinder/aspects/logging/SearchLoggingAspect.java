package com.illustrationfinder.aspects.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

/**
 * Created by alombard on 19/11/2015.
 */
@Aspect
public class SearchLoggingAspect {
    private static final String SEARCH_LOGGER_NAME = "Search";

    @After("args(search) && execution(* com.illustrationfinder.searchengine.ISearchEngine.search(Object))")
    void logAfterSearch(JoinPoint joinPoint, Object search) {
        Logger.getLogger(SEARCH_LOGGER_NAME).info(search.toString() + " researched using " + joinPoint.getThis().toString());
    }
}
