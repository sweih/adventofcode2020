package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day03 extends Day {

    public Object part1(List<String> input) {
        return Long.toString(executeSlope(input,3,1));
    }

    public Object part2(List<String> input) {
        long r;

        r = executeSlope(input,1,1) *
                executeSlope(input,3,1 ) *
                executeSlope(input,5,1) *
                executeSlope(input,7,1) *
                executeSlope(input, 1,2);
        return Long.toString(r);
    }

    private long executeSlope(List<String> input, Integer stepsleft, Integer stepsdown) {
        int maxlines = input.size();

        int xcounter = 0;
        int ycounter = 0;

        long trees = 0;

        while (ycounter < maxlines) {
            if (this.isTree(xcounter,ycounter,input)) trees++;
            ycounter+= stepsdown;
            xcounter+= stepsleft;
        }
        return trees;
    }

    private boolean isTree(Integer x, Integer y, List<String> list) {
        String row = list.get(y);
        Integer rowlength = row.length();
        Integer position = x % rowlength;
        return ('#' == row.charAt(position));
    }

}
