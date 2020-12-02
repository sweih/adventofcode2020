package de.beachboys.aoc2020;

import de.beachboys.aoc2020.ParsedCommandLine;

public interface PasswordPolicy {

    public boolean complies(ParsedCommandLine pcl);

}
