package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Day18 extends Day {

    public Object part1(List<String> input) {
        List<String> rpn = new ArrayList<>();
        ShuntingYard sy = new ShuntingYard();
            for (String line : input) {
                rpn.add( sy.postfix(line.replace(" ", "")));
            }

            long sum = 0;
            for (String r:rpn) {
                Stack<Long> stack = new Stack<>();
                for (int i=0;i< r.length();i++) {

                   String tmp = r.substring(i,i+1);
                    try {
                        long intNumOrOperand = Long.valueOf(tmp);
                        stack.push(intNumOrOperand);
                    } catch (Exception e) {
                        if (tmp.equals("*")) {
                            stack.push(stack.pop() * stack.pop());
                        } else if (tmp.equals("+")) {
                            stack.push(stack.pop() + stack.pop());
                        }
                    }
                }
                long j = stack.pop();

                sum+=j;
            }

        return Long.toString(sum);
    }

    public Object part2(List<String> input) {
        return 2;
    }



}
