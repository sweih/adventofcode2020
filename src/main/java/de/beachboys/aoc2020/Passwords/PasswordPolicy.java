package de.beachboys.aoc2020.Passwords;

import de.beachboys.aoc2020.Passwords.ParsedCommandLine;

public interface PasswordPolicy {

    public boolean complies(ParsedCommandLine pcl);

}
