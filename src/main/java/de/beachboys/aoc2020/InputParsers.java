package de.beachboys.aoc2020;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputParsers {

    public static List<List<String>> splitByEmptyLines(List<String> input) {
            List<String> chunk = new ArrayList<>();
            List<List<String>> chunks = new ArrayList<>();
            for (String row: input) {
                if (row.trim().equals("")) {
                    chunks.add(chunk);
                    chunk = new ArrayList<>();
                } else {
                    chunk.add(row);
                }
            }
            chunks.add(chunk);
            return chunks;
    }

    public static List<String> splitByEmptyLinesAndConcat(List<String> input, String delemiter) {
        List<List<String>> tmp = splitByEmptyLines(input);
        return tmp.stream().map(k->k.stream().collect(Collectors.joining( delemiter ))).collect(Collectors.toList());
    }
}
