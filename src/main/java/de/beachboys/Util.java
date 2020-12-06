package de.beachboys;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Util {

    public static List<String> parseToList(String input, String separator) {
        return Arrays.asList(input.split(separator));
    }

    public static List<Integer> parseToIntList(String input, String separator) {
        return Arrays.stream(input.split(separator)).map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<Long> parseToLongList(String input, String separator) {
        return Arrays.stream(input.split(separator)).map(Long::valueOf).collect(Collectors.toList());
    }

    public static List<String> parseCsv(String input) {
        return parseToList(input, ",");
    }

    public static List<Integer> parseIntCsv(String input) {
        return parseToIntList(input, ",");
    }

    public static List<Long> parseLongCsv(String input) {
        return parseToLongList(input, ",");
    }

    public static long greatestCommonDivisor(long long1, long long2) {
        if (long1 == long2 || long2 == 0) {
            return long1;
        }
        return greatestCommonDivisor(long2, long1 % long2);
    }

    public static long leastCommonMultiple(long long1, long long2) {
        return long1 * (long2 / greatestCommonDivisor(long1, long2));
    }

}
