package de.beachboys;

import org.javatuples.Pair;

import java.util.*;
import java.util.function.Function;
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

    public static int countUniqueCharacters(String input) {
        boolean[] isItThere = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < input.length(); i++) {
            isItThere[input.charAt(i)] = true;
        }

        int count = 0;
        for (boolean b : isItThere) {
            if (b) {
                count++;
            }
        }

        return count;
    }

    public static List<String> splitStringBySize(String str, int size) {
        ArrayList<String> split = new ArrayList<>();
        for (int i = 0; i < str.length() / size; i++) {
            split.add(str.substring(i * size, Math.min((i + 1) * size, str.length())));
        }
        return split;
    }

    public static long countOccurrences(String someString, char searchedChar, int index) {
        if (index >= someString.length())
            return 0;
        long count = someString.charAt(index) == searchedChar ? 1 : 0;
        return count + countOccurrences(
                someString, searchedChar, index + 1);
    }


    public static String paintMap(Map<Pair<Integer, Integer>, String> map) {
        Map<String, String> valuesToPaint = map.values().stream().distinct().collect(Collectors.toMap(Function.identity(), Function.identity()));
        return paintMap(map, valuesToPaint);
    }

    public static String paintMap(Map<Pair<Integer, Integer>, String> map, Map<String, String> valuesToPaint) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Pair<Integer, Integer> point : map.keySet()) {
            minX = Math.min(minX, point.getValue0());
            minY = Math.min(minY, point.getValue1());
            maxX = Math.max(maxX, point.getValue0());
            maxY = Math.max(maxY, point.getValue1());
        }
        int width = maxX - minX + 1;
        int height = maxY - minY + 1;
        StringBuilder imageString = new StringBuilder();
        imageString.append(" ".repeat(width*height));
        for (Pair<Integer, Integer> point : map.keySet()) {
            int index = width * (point.getValue1() - minY) + (point.getValue0() - minX);
            imageString.replace(index, index + 1, map.get(point));
        }
        return formatImage(imageString.toString(), width, height, valuesToPaint);
    }

    public static String formatImage(String imageString, int width, int height, Map<String, String> valuesToPaint) {
        final StringBuilder newImageString = new StringBuilder();
        if (valuesToPaint != null) {
            imageString.chars()
                    .forEach(i -> {
                        String charAsString = String.valueOf((char) i);
                        newImageString.append(valuesToPaint.getOrDefault(charAsString, " "));
                    });
        } else {
            newImageString.append(imageString);
        }
        return formatImage(newImageString.toString(), width, height);
    }

    public static String formatImage(String imageString, int width, int height) {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < height; i++) {
            returnValue.append(imageString, i*width, (i+1)*width);
            returnValue.append("\n");
        }
        return returnValue.toString();
    }

    public static Map<Pair<Integer, Integer>, String> buildImageMap(String imageWithLineBreaks) {
        return buildImageMap(parseToList(imageWithLineBreaks, "\n"));
    }

    public static Map<Pair<Integer, Integer>, String> buildImageMap(List<String> imageLines) {
        Map<Pair<Integer, Integer>, String> map = new HashMap<>();
        for (int j = 0; j < imageLines.size(); j++) {
            String line = imageLines.get(j);
            for (int i = 0; i < line.length(); i++) {
                map.put(Pair.with(i, j), line.substring(i, i + 1));
            }
        }
        return map;
    }

    public static List<Long> rangeOf(List<Long> list, int start, int end) {
        List<Long> result = new ArrayList<>();
        for (int i=start; i<=end; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public static List<Integer> rangeOfInt(List<Integer> list, int start, int end) {
        List<Integer> result = new ArrayList<>();
        for (int i=start; i<=end; i++) {
            result.add(list.get(i));
        }
        return result;
    }


}

