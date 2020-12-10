package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;
import org.javatuples.Pair;

import java.util.*;

public class Day10 extends Day {

    public Long[][] store;

    public Object part1(List<String> input) {
        List<Integer> il = new ArrayList<>();
        for (String line : input) il.add(Integer.parseInt(line));
        il.add(0);
        Collections.sort(il);

        int os = 0;
        int ts = 1;

            for (int j = 1; j < il.size(); j++) {
                Integer a = il.get(j);
                Integer b = il.get(j-1);
                if (a-b == 1) {
                     os++;
                 } else if (a-b == 3) {
                     ts++;
                 }
            }
        return Integer.toString(os * ts);

    }




    public Object part2(List<String> input) {

        List<Integer> il = new ArrayList<>();
        for (String line : input) il.add(Integer.parseInt(line));
        Collections.sort(il);

        int helper = Collections.max(il) + 1;
        this.store = new Long[helper][helper];
        for (int i = 0; i < helper; i++) {
            for (int j = 0; j < helper; j++) {
                this.store[i][j] = -1L;
            }

        }

        return Long.toString(countIt(il, 0, Collections.max(il) + 3));
    }


    public long countIt(List<Integer> l, int start, int goal) {
        int len = l.size();
        long counter = 0;
        if (this.store[len][start] != -1) return this.store[len][start];

        if ((goal - start) <= 3) counter++;
        if (l.size() == 0) return counter;

        if ((l.get(0) - start) <= 3) {
            counter = counter + countIt(Util.rangeOfInt(l,1,l.size()-1), l.get(0), goal);
        }
        counter = counter + countIt(Util.rangeOfInt(l,1,l.size()-1), start, goal);
        this.store[len][start] = counter;
        return counter;
    }



}
