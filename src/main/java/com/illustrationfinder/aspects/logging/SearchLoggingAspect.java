package com.illustrationfinder.aspects.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;

/**
 * Created by alombard on 19/11/2015.
 */
@Aspect
public class SearchLoggingAspect {
    private static final String SEARCH_LOGGER_NAME = "Search";

    @Before("args(search) && execution(* com.illustrationfinder.process.searchengine.ISearchEngine.search(Object))")
    public void logBeforeSearch(JoinPoint joinPoint, Object search) {
        Logger.getLogger(SEARCH_LOGGER_NAME).info("Researching \"" + search.toString() + "\" using " + joinPoint.getThis().toString());
    }
}
