package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;

import java.lang.reflect.Array;
import java.util.*;

public class Day09 extends Day {

    public Object part1(List<String> input) {

        List<Long> il = new ArrayList<>();
        for (String line : input) il.add(Long.parseLong(line));

        for (int i=25;i<il.size();i++) {
            if (!isSumOf(il, il.get(i), i-25)) return Long.toString(il.get(i));
        }
        return "part1result";
    }

    public boolean isSumOf(List<Long> factors, Long check, Integer idx) {
        for (int i = idx; i<idx+25;i++) {
            for (int j=idx;j<idx+25; j++) {
                if ((factors.get(i) + factors.get(j)) == check) return true;
            }
        }
        return false;
    }

    public Object part2(List<String> input) {
        List<Long> il = new ArrayList<>();
        for (String line : input) il.add(Long.parseLong(line));

        long num = 1124361034;
        long sum = 0;

        List<Long> list = new ArrayList<>();

        for (Long line : il) {
            sum += line;
            list.add(sum);
            for (int i = 0; i < list.size(); i++) {
                for (int j=i; j < list.size(); j++) {
                    if (list.get(j) - list.get(i) == num) {
                        return Long.toString(Collections.min(Util.rangeOf(il, i+1, j)) + Collections.max(Util.rangeOf(il, i+1, j)));
                    }
                }
            }
        }

    return "2" ;
    }

}
