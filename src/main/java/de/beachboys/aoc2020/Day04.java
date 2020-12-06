package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 extends Day {

    public Object part1(List<String> input) {
        return parseAndCalculate(input, this::isValidPassportPart1);
    }

    public Object part2(List<String> input) {
        return parseAndCalculate(input, this::isValidPassportPart2);
    }

    private String parseAndCalculate(List<String> input, Predicate<List<List<String>>> func) {
        Integer validPassports = 0;
        List<List<List<String>>> passports = parsePassports(input);
        for (List<List<String>> passport : passports) {
            if (func.test(passport)) validPassports++;
        }
        return validPassports.toString();
    }


    private List<List<List<String>>> parsePassports(List<String> input) {
        List<String> tmp = InputParsers.splitByEmptyLinesAndConcat(input, " ");
        RParser rparser = new RParser("([\\w]+):([\\w|#]+)");
        List<List<List<String>>> passports = rparser.parseToStringListMultiple( tmp,2,false);
        return passports;
    }

    public boolean isValidPassportPart1(List<List<String>> passport) {
        int hitcounter = 0;
        for(List<String> ids: passport) {
            if (ids.get(0).equals("byr")) hitcounter++;
            if (ids.get(0).equals("iyr")) hitcounter++;
            if (ids.get(0).equals("eyr")) hitcounter++;
            if (ids.get(0).equals("hgt")) hitcounter++;
            if (ids.get(0).equals("hcl")) hitcounter++;
            if (ids.get(0).equals("ecl")) hitcounter++;
            if (ids.get(0).equals("pid")) hitcounter++;
            //if (passport.get(0).equalsIgnoreCase("cid")) hitcounter++;
        }

        return (hitcounter == 7);
    }


    public boolean isValidPassportPart2(List<List<String>> passportraw) {
        int hitcounter = 0;
        for(List<String> passport:passportraw) {
            if (passport.get(0).equals("byr")) {
                hitcounter+= isValidRange(passport.get(1), 1920,2002);
            }
            if (passport.get(0).equals("iyr")) {
                hitcounter += isValidRange(passport.get(1), 2010,2020);
            }
            if (passport.get(0).equals("eyr"))  {
                hitcounter += isValidRange(passport.get(1), 2020,2030);
            }
            if (passport.get(0).equals("hgt"))  {
                String height = passport.get(1).trim();

                Pattern pattern =  Pattern.compile("(\\d+)(cm|in)");
                Matcher matcher  =  pattern.matcher(height);
                if (!matcher.find()) continue;
                if (matcher.groupCount() != 2) continue;

                if (matcher.group(2).equalsIgnoreCase("cm"))
                  hitcounter += this.isValidRange(matcher.group(1), 150,193);

                if (matcher.group(2).equalsIgnoreCase("in"))
                    hitcounter += this.isValidRange(matcher.group(1), 59,76);
            }

            if (passport.get(0).equals("hcl")) {
                String hairColor = passport.get(1).trim();
                 if (hairColor.matches("#[0-9a-f]{6}")) hitcounter++;
            }
            if (passport.get(0).equals("ecl")) {
                String ecl = passport.get(1).trim().toLowerCase();
                if (ecl.matches("amb|blu|brn|gry|grn|hzl|oth")) hitcounter++;
            }
            if (passport.get(0).equalsIgnoreCase("pid")) {
                if (passport.get(1).trim().length() == 9) hitcounter++;
            }
         }

        return (hitcounter == 7);
    }

    private int isValidRange(String value1, int min, int max) {
        Integer value = Integer.parseInt(value1);
        if ((value>=min) && (value <= max)) {
            return 1;
        }
        return 0;
    }

}
