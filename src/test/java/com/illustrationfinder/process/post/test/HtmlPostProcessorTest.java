package com.illustrationfinder.process.post.test;

/*
 * #%L
 * Illustration finder
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


import com.illustrationfinder.process.post.HtmlPostProcessor;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Alexandre on 19/11/2015.
 */
public class HtmlPostProcessorTest {
//    @Test
    public void testHtmlPostProcessor() throws MalformedURLException {
        final HtmlPostProcessor htmlPostProcessor = new HtmlPostProcessor();
        htmlPostProcessor.setUrl(new URL("http://www.hankerspace.com/fr/injection-de-code-a-froid-dans-un-executable-windows/"));

        final List<String> keywords = htmlPostProcessor.generateKeywords();

        System.out.println(keywords);
    }
}
