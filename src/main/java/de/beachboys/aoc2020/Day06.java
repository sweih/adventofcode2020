package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 extends Day {

    public Object part1(List<String> input) {

        List<List<String>> answers = InputParsers.splitByEmptyLines(input);
        return Integer.toString(answers.stream().mapToInt(k -> getAnswers(k)).sum());

    }

    private Integer getAnswers(List<String> answer) {
      String checker = "";
        for (String line : answer) {
          checker = checker + line;
      }

        return Util.countUniqueCharacters(checker);
    }

    public Object part2(List<String> input) {

        List<List<String>> answers = InputParsers.splitByEmptyLines(input);
        return Integer.toString( answers.stream().mapToInt(k -> getCommonAnswers(k)).sum());
    }

    private Integer getCommonAnswers(List<String> answer) {

        List<List<String>> answers = new ArrayList<>();
        List<String> yeses;
        Integer result = 50;


        for (String line : answer) {
            yeses = new ArrayList<>();
            for (int i=0; i< line.length(); i++) {
                yeses.add(line.substring(i,i+1));
            }
            answers.add(yeses);
        }

        for (List<String> yes1 : answers) {
          for (List<String> yes2 : answers ) {
               yes1.retainAll(yes2);
              result = Math.min(yes1.size(),result);
          }
        }
        return result;
    }


}
