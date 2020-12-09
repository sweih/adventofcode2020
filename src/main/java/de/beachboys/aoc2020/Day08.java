package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day08 extends Day {

    public Object part1(List<String> input) {
        Assembler ass = new Assembler(input);
        ass.runProgram();
        return Integer.toString(ass.ax);
    }

    public Object part2(List<String> input) {
        Assembler ass = new Assembler(input);
        ass.fixProgram();
        ass.runProgram();
        return Integer.toString(ass.ax);
    }

}
