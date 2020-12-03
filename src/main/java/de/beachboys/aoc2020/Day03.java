package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.IOHelper;
import de.beachboys.aoc2020.Matrix.*;
import org.javatuples.Pair;

import java.util.List;
import java.util.Set;

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
        IOHelper io = new IOHelper();
        MatrixTraverserXY mt = new MatrixTraverserXY(stepsleft, stepsdown);
        MatrixComputer mc = new MatrixComputer(input, mt, io);

        long slopes = 0;
        while (mc.next()) {
            if ('#' == mc.nextChar()) slopes++;
        }
        return slopes;

    }

    // just playing along;
    private long pingpong(List<String> input) {
        MatrixTraverserXY mt = new MatrixTraverserXY(1,1);
        mt.modeX = OverflowMode.BOUNCE;
        mt.modeY = OverflowMode.BOUNCE;
        MatrixComputer mc = new MatrixComputer(input, mt, new IOHelper());
        System.out.println("Starcount=" + mc.matrix.countChars('.'));
        int counter = 0;
        while (mc.next() && counter < 60000) {
            char c = mc.nextChar();
            mc.replaceCurrentChar('X');
            //if (c=='#') ((MatrixTraverserXY)mc.traverser).mirror();
            //System.out.println(mc.matrix.countChars('.'));
            counter ++;
        }
        mc.matrix.print();
      return mc.matrix.countChars('.');
    }

}
