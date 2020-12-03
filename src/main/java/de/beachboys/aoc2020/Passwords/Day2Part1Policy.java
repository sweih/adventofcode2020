package de.beachboys.aoc2020.Passwords;

public class Day2Part1Policy implements PasswordPolicy {

    @Override
    public boolean complies(ParsedCommandLine pcl) {

        Integer numberOfAppearances=0;
        for(int i=0;i<pcl.password.length();i++) {
            if (pcl.isCheckerAt(i)) {
                numberOfAppearances++;
            }
        }

        return ((numberOfAppearances >= pcl.min) && (numberOfAppearances <= pcl.max));

    }
}
