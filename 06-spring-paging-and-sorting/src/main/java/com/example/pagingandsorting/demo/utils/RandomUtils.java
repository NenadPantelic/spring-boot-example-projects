package com.example.pagingandsorting.demo.utils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static String generateRandomText(final int maxLength, boolean lowerAlphaOnly) {
        int startChar = (int) '!';
        int endChar = (int) '~';
        if (lowerAlphaOnly) {
            startChar = 'a';
            endChar = 'z';
        }
        return generateRandomString(maxLength, startChar, endChar);
    }

    public static <T> List<T> getRandomSublist(List<T> values, int size) {
        Collections.shuffle(values);
        return values.subList(0, size);
    }

    public static <T> T getRandomElement(T[] elements) {
        return elements[getRandomInt(elements.length)];
    }

    public static int getRandomPositiveInt(int maxVal) {
        return getRandomInt(maxVal) + 1;
    }

    public static BigDecimal getRandomDecimal() {
        return BigDecimal.valueOf(RANDOM.nextDouble() * 1000);
    }

    private static String generateRandomString(final int maxLength, int startChar, int endChar) {
        return RANDOM.ints(maxLength, startChar, endChar + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    private static int getRandomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

}