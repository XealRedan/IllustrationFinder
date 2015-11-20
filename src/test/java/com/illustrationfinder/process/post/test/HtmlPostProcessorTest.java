package com.illustrationfinder.process.post.test;

import com.illustrationfinder.process.post.HtmlPostProcessor;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alexandre on 19/11/2015.
 */
public class HtmlPostProcessorTest {
    @Test
    public void testHtmlPostProcessor() throws MalformedURLException {
        final HtmlPostProcessor htmlPostProcessor = new HtmlPostProcessor();
        htmlPostProcessor.setUrl(new URL("http://www.hankerspace.com/fr/injection-de-code-a-froid-dans-un-executable-windows/"));

        final String keywords = htmlPostProcessor.generateKeywords();

        System.out.println(keywords);
    }
}
