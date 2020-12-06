package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day05 extends Day {

    public Object part1(List<String> input) {
        return Integer.toString(input.stream().mapToInt(k -> getSeatIDFromSeatCode(k)).max().orElse(0));
    }

    private Integer getSeatIDFromSeatCode(String seat) {

        String res = seat.replace('F', '0').replace('B', '1').replace('R', '1').replace('L','0');
        return Integer.parseInt(res,2);
    }

    public Object part2(List<String> input) {

        List<Integer> list = input.stream().map(k->getSeatIDFromSeatCode(k)).sorted().collect(Collectors.toList());

        Integer lastRow =-10;
        for (Integer i: list) {
          if (lastRow == i-2) return Integer.toString(i-1);
          lastRow = i;
        }

        return -100;
    }

}
