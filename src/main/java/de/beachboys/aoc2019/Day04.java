package de.beachboys.aoc2019;

import de.beachboys.Day;

import java.util.*;

public class Day04 extends Day {

    @Override
    public Object part1(List<String> input) {
        long min = 382345;
        long max = 843167;

        long counter = 0;
        for (long i=min;i<=max;i++) {
          if (isValidPassword(i)) counter++;
        }

        return Long.toString(counter);
    }

    public boolean isValidPassword(Long password) {
        String pws = password.toString();
        char[] cs = pws.toCharArray();
        return (digetsAlwaysIncrease(cs)) && twoNumbers(cs);

    }

    public boolean isValidPassword2(Long password) {
        String pws = password.toString();
        char[] cs = pws.toCharArray();

        return (digetsAlwaysIncrease(cs)  && checkPart2(cs));

    }

    private boolean twoNumbers(char[] cs) {
        char last = '/';
        int counter = 0;
        for (int i=0;i<cs.length;i++) {
            if (cs[i] == last) counter++;
            last = cs[i];
        }
        return (counter > 0);
    }

    private boolean checkPart2(char[] cs) {
        char last = '/';
        char lastlast = '/';
        Integer[] repetitions = {1,1,1,1,1,1};

        for (int i=1;i<cs.length;i++) {
            if (cs[i] == cs[i-1]) repetitions[i] = 1 + repetitions[i-1];
            if (repetitions[i] > repetitions[i-1]) repetitions[i-1] = 1;
        }

        return Arrays.stream(repetitions).anyMatch(k -> k==2);
    }


    private boolean digetsAlwaysIncrease(char[] cs) {
        char last = '/';
        for (int i=0;i<cs.length;i++) {
          if (cs[i] < last) return false;
          last = cs[i];
        }
        return true;
    }

    @Override
    public Object part2(List<String> input) {
        long min = 382345;
        long max = 843167;

        long counter = 0;
        for (long i=min;i<=max;i++) {
            if (isValidPassword2(i)) counter++;
        }

        return Long.toString(counter);
    }
}
