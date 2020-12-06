package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 extends Day {

    public Object part1(List<String> input) {

        List<List<String>> answers = parseAnswers(input);

        Integer correctAnswers = 0;
        for (List<String> answer : answers) {
           correctAnswers += getAnswers(answer);
        }
        return Integer.toString(correctAnswers);
    }

    private Integer getAnswers(List<String> answer) {
      String checker = "";
        for (String line : answer) {
          checker = checker + line;
      }
        return countUniqueChars(checker);
    }

    public static int countUniqueChars(String input) {
        boolean[] ce = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < input.length(); i++) {
            ce[input.charAt(i)] = true;
        }

        int count = 0;
        for (int i = 0; i < ce.length; i++) {
            if (ce[i] == true){
                count++;
            }
        }

        return count;
    }

    private List<List<String>> parseAnswers(List<String> input) {
        List<String> answer = new ArrayList<>();
        List<List<String>> answers = new ArrayList<>();
        for (String row: input) {
            if (row.trim().equals("")) {
                answers.add(answer);
                answer = new ArrayList<>();
            } else {
                answer.add(row);
            }
        }
        answers.add(answer);
        return answers;
    }


    public Object part2(List<String> input) {
        List<List<String>> answers = parseAnswers(input);

        Integer correctAnswers = 0;
        for (List<String> answer : answers) {
            correctAnswers += getCommonAnswers(answer);
        }
        return Integer.toString(correctAnswers);

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
