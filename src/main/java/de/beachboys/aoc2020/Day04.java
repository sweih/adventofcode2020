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

public class Day04 extends Day {

    public Object part1(List<String> input) {
        return parseAndCalculate(input, this::isValidPassportPart1);
    }

    public Object part2(List<String> input) {
        return parseAndCalculate(input, this::isValidPassportPart2);
    }

    private String parseAndCalculate(List<String> input, Predicate<Set<Pair<String,String>>> func) {
        Integer validPassports = 0;
        List<Set<Pair<String, String>>> passports = parsePassports(input);
        for (Set<Pair<String,String>> passport : passports) {
            if (func.test(passport)) validPassports++;
        }
        return validPassports.toString();
    }


    private List<Set<Pair<String, String>>> parsePassports(List<String> input) {
        Set<Pair<String,String>> passport = new HashSet<>();
        List<Set<Pair<String,String>>> passports = new ArrayList<>();
        for (String row: input) {
              if (row.trim().equals("")) {
                 passports.add(passport);
                 passport = new HashSet<>();
            } else {
                List<String> line = Util.parseToList(row, " ");
                for (String element:line) {
                  String[] chunks = element.split(":");
                  if (chunks.length == 2) {
                      passport.add(new Pair<>(chunks[0].trim(), chunks[1].trim()));
                  } else {
                      throw new IllegalStateException("Ooops :-(");
                  }
                }
            }
        }
        passports.add(passport);
        return passports;
    }

    public boolean isValidPassportPart1(Set<Pair<String,String>> passports) {
        int hitcounter = 0;
        for(Pair<String,String> passport:passports) {
            if (passport.getValue0().equals("byr")) hitcounter++;
            if (passport.getValue0().equals("iyr")) hitcounter++;
            if (passport.getValue0().equals("eyr")) hitcounter++;
            if (passport.getValue0().equals("hgt")) hitcounter++;
            if (passport.getValue0().equals("hcl")) hitcounter++;
            if (passport.getValue0().equals("ecl")) hitcounter++;
            if (passport.getValue0().equals("pid")) hitcounter++;
            //if (passport.getValue0().equalsIgnoreCase("cid")) hitcounter++;
        }

        return (hitcounter == 7);
    }


    public boolean isValidPassportPart2(Set<Pair<String,String>> passports) {
        int hitcounter = 0;
        for(Pair<String,String> passport:passports) {
            if (passport.getValue0().equals("byr")) {
                hitcounter+= isValidRange(passport.getValue1(), 1920,2002);
            }
            if (passport.getValue0().equals("iyr")) {
                hitcounter += isValidRange(passport.getValue1(), 2010,2020);
            }
            if (passport.getValue0().equals("eyr"))  {
                hitcounter += isValidRange(passport.getValue1(), 2020,2030);
            }
            if (passport.getValue0().equals("hgt"))  {
                String height = passport.getValue1().trim();

                Pattern pattern =  Pattern.compile("(\\d+)(cm|in)");
                Matcher matcher  =  pattern.matcher(height);
                if (!matcher.find()) continue;
                if (matcher.groupCount() != 2) continue;

                if (matcher.group(2).equalsIgnoreCase("cm"))
                  hitcounter += this.isValidRange(matcher.group(1), 150,193);

                if (matcher.group(2).equalsIgnoreCase("in"))
                    hitcounter += this.isValidRange(matcher.group(1), 59,76);
            }

            if (passport.getValue0().equals("hcl")) {
                String hairColor = passport.getValue1().trim();
                 if (hairColor.matches("#[0-9a-f]{6}")) hitcounter++;
            }
            if (passport.getValue0().equals("ecl")) {
                String ecl = passport.getValue1().trim().toLowerCase();
                if (ecl.matches("amb|blu|brn|gry|grn|hzl|oth")) hitcounter++;
            }
            if (passport.getValue0().equalsIgnoreCase("pid")) {
                if (passport.getValue1().trim().length() == 9) hitcounter++;
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
