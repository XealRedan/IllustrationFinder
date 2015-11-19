package com.illustrationfinder.aspects.logging;

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


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;

/**
 * Logging aspect for search engine
 */
@Aspect
public class SearchLoggingAspect {
    private static final String SEARCH_LOGGER_NAME = "Search";

    @Before("args(search) && execution(* com.illustrationfinder.process.searchengine.ISearchEngine.search(Object))")
    public void logBeforeSearch(JoinPoint joinPoint, Object search) {
        Logger.getLogger(SEARCH_LOGGER_NAME).info("Researching \"" + search.toString() + "\" using " + joinPoint.getThis().toString());
    }
}
