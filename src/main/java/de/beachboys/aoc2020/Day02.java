package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;

import java.util.List;

import static de.beachboys.aoc2020.computeMode.ODER;

public class Day02 extends Day {

    public Object part1(List<String> input) {
        PolicyComputer pc = new PolicyComputer(input, io);
        pc.registerPolicy(new Day2Part1Policy());
        return pc.correctPasswordCountStr();
    }

    public Object part2(List<String> input) {
        PolicyComputer pc = new PolicyComputer(input, io);
        pc.registerPolicy(new Day2Part2Policy());
        return pc.correctPasswordCountStr();
    }


}
