package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.aoc2020.Passwords.Day2Part1Policy;
import de.beachboys.aoc2020.Passwords.Day2Part2Policy;
import de.beachboys.aoc2020.Passwords.PolicyComputer;

import java.util.List;

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
