package com.jdroid.java.utils;

import java.text.Normalizer;

public class Sanitizer {

	public static String plainString(String text) {
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);

        String plain = nfdNormalizedString.replaceAll("[^a-zA-Z0-9\\s]", "");
        plain = plain.replaceAll("\\s{2,}", " ").trim();
        return plain;
    }

	public static String plainStringWithoutNumbers(String text) {
        String withoutNumbers = removeNumbers(text);
        return plainString(withoutNumbers);
    }

    public static String justNumbers(String text) {
        return text.replaceAll("[^\\d]", "");
    }

    public static String removeNumbers(String text) {
        return text.replaceAll("\\d", "");
    }
}
