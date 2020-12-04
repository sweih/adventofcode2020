package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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
                if (isValidRange(passport.getValue1(), 1920,2002)) hitcounter++;
            }
            if (passport.getValue0().equals("iyr")) {
                if (isValidRange(passport.getValue1(), 2010,2020)) hitcounter++;
            }
            if (passport.getValue0().equals("eyr"))  {
                if (isValidRange(passport.getValue1(), 2020,2030)) hitcounter++;
            }
            if (passport.getValue0().equals("hgt"))  {
                String height = passport.getValue1().trim();
                if ( height.indexOf("cm") > 0) {
                    height = height.substring(0,height.indexOf("cm") );
                    Integer hi = Integer.parseInt(height);
                    if ((hi >=150) && (hi<=193)) hitcounter++;
                }

                if ( height.indexOf("in") > 0) {
                    height = height.substring(0,height.indexOf("in") );
                    Integer hi = Integer.parseInt(height);
                    if ((hi >=59) && (hi<=76)) hitcounter++;
                }
            }
            if (passport.getValue0().equals("hcl")) {
                String hairColor = passport.getValue1().trim();
                 if (hairColor.matches("#[0-9a-f]{6}")) hitcounter++;
            }
            if (passport.getValue0().equals("ecl")) {
                String ecl = passport.getValue1().trim();
                if (ecl.equalsIgnoreCase("amb") ||
                        ecl.equalsIgnoreCase("blu") ||
                        ecl.equalsIgnoreCase("brn") ||
                        ecl.equalsIgnoreCase("gry") ||
                        ecl.equalsIgnoreCase("grn") ||
                        ecl.equalsIgnoreCase("hzl") ||
                        ecl.equalsIgnoreCase("oth")
                )

                hitcounter++;
            }
            if (passport.getValue0().equalsIgnoreCase("pid")) {
                if (passport.getValue1().trim().length() == 9) hitcounter++;
            }
         }

        return (hitcounter == 7);
    }

    private boolean isValidRange(String value1, int min, int max) {
        Integer value = Integer.parseInt(value1);
        return ((value>=min) && (value <= max));
    }

}
