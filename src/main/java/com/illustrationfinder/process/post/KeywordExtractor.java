package com.illustrationfinder.process.post;

import java.util.*;

/**
 * Created by Alexandre on 19/11/2015.
 */
public class KeywordExtractor {

    public KeywordExtractor() {
        //
    }

    public String getKeywords(String text) {
        final String lowerCase = text.toLowerCase();

        final String[] sentences = lowerCase.split("\\.");

        final Map<String, Integer> frequencies = new HashMap<>();

        for(String sentence : sentences) {
            final String withoutPunctuation = sentence.replaceAll("[\\.,\\?!:;]", "");
            final String[] words = withoutPunctuation.split(" ");

            final HashMap<String, Integer> wordFrequency = new HashMap<>();

            if(words.length >= 3) {
                for(final String word : words) {
                    final String trimmedWord = word.trim();

                    if(frequencies.containsKey(trimmedWord)) {
                        frequencies.put(trimmedWord, frequencies.get(trimmedWord) + 1);
                    } else {
                        frequencies.put(trimmedWord, 1);
                    }
                }
            }
        }

        final TreeMap<Integer, List<String>> ordered = new TreeMap<>();

        frequencies.forEach((k, v) -> {
            if(ordered.containsKey(v))
                ordered.get(v).add(k);
            else {
                final List<String> list = new ArrayList<>();
                list.add(k);
                ordered.put(v, list);
            }
        });

        // TODO
        return null;
    }
}
