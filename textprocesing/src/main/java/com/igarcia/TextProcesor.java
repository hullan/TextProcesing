package com.igarcia;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextProcesor {

    private String formatText(String text) {
        return text.replace("\n", " ").replace("\r", " ").replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
    }

    private String[] splitEspace(String text) {
        return formatText(text).split(" ");
    }

    public int numberWords(String text) {
        return splitEspace(text).length;
    }

    public String numberWordsText(String text) {
        return "The text has in total " + numberWords(text) + " words";
    }

    public String header(String text) {
        int topWords = numberWords(text);
        if (topWords > 10)
            topWords = 10;
        return "Those are the top " + topWords + " words used:\n\n";
    }

    public List<String> topWords(String text) {
        return Arrays.asList(splitEspace(text)).stream().collect(Collectors.groupingBy(t -> t, Collectors.counting()))
                .entrySet().stream().sorted(
                        Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public String bodyText(String text) {
        StringBuilder result = new StringBuilder();
        List<String> topWords = topWords(text);
        int length = topWords.size();
        IntStream.range(1, length + 1).forEach(i -> result.append(i + ". " + topWords.get(i - 1) + "\n"));
        return result.toString();
    }

    public String allText(String text) {
        return header(text) + bodyText(text) + numberWordsText(text);
    }
}
