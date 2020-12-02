package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day01 extends Day {

    public Object part1(List<String> input) {
        for (int i=0; i<input.size();i++) {
            for (int j=i;j<input.size();j++) {
                if ((Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(j))) == 2020)
                    return String.valueOf(Integer.parseInt(input.get(i)) * Integer.parseInt(input.get(j)));
            }
        }
        return "";

    }

    public Object part2(List<String> input) {
        for (int i=0; i<input.size();i++) {
            for (int j=i;j<input.size();j++) {
                for (int k = j; k < input.size(); k++) {

                    if ((Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(j)) + Integer.parseInt(input.get(k))) == 2020)
                        return String.valueOf(Integer.parseInt(input.get(i)) * Integer.parseInt(input.get(j)) * Integer.parseInt(input.get(k)));

                }
            }
        }
        return "";
    }

}
