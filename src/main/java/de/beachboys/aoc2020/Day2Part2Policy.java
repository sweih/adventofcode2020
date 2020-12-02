package de.beachboys.aoc2020;

public class Day2Part2Policy implements PasswordPolicy {

    @Override
    public boolean complies(ParsedCommandLine pcl) {
        Integer nums=0;

        if (pcl.min <= pcl.password.length()) {
            if (pcl.isCheckerAt((int) (pcl.min - 1))) nums++;
        }

        if (pcl.max <= pcl.password.length()) {
            if (pcl.isCheckerAt((int)pcl.max - 1)) nums++;
        }

        return (nums==1);
    }
}
