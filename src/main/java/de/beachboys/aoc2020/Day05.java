package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Day05 extends Day {

    public Object part1(List<String> input) {

        int max = 0;
     for (String seat: input) {
            Integer result = getSeatIDFromSeatCode(seat);
            if (result > max) max = result;
       }

        return Integer.toString(max);
    }

    private Integer getSeatIDFromSeatCode(String seat) {
        String row = seat.substring(0,7);
        String col = seat.substring(7);

        row = row.replace('F', '0');
        row = row.replace('B', '1');

        Integer rowI = Integer.parseInt(row, 2);

        col = col.replace('R', '1');
        col = col.replace('L','0');
        Integer colI = Integer.parseInt(col,2);

        Integer result = (rowI * 8) + colI;
        return result;
    }

    public Object part2(List<String> input) {

        TreeSet<Integer> set = new TreeSet<>();
        for (String seat: input) {
            set.add(getSeatIDFromSeatCode(seat));
        }


        Integer lastRow =-10;
        for (Integer i: set) {
          if (lastRow == i-2) return Integer.toString(i-1);
          lastRow = i;
        }

        return -100;
    }

}
