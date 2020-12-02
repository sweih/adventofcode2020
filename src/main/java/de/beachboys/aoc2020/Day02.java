package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;

import java.util.List;

public class Day02 extends Day {

    public Object part1(List<String> input) {
        Integer counter=0;
        for (int i=0; i<input.size();i++) {
            List<String> list1 = Util.parseToList(input.get(i), ":");
            List<String> list2 = Util.parseToList(list1.get(0), "-");
            List<String> list3 = Util.parseToList(list2.get(1), " ");

            Integer min = Integer.valueOf(list2.get(0));
            Integer max = Integer.valueOf(list3.get(0));
            String check = list3.get(1).trim();
            String password = list1.get(1).trim();
            io.logDebug(min.toString() + '-' + max.toString() + '-' + check+"-"+password);

            if (checkPassword(password, min, max, check) )
               counter++;

        }


        return counter.toString();
    }

    public Object part2(List<String> input) {
        Integer counter=0;
        for (int i=0; i<input.size();i++) {
            List<String> list1 = Util.parseToList(input.get(i), ":");
            List<String> list2 = Util.parseToList(list1.get(0), "-");
            List<String> list3 = Util.parseToList(list2.get(1), " ");

            Integer min = Integer.valueOf(list2.get(0));
            Integer max = Integer.valueOf(list3.get(0));
            String check = list3.get(1).trim();
            String password = list1.get(1).trim();
            io.logDebug(min.toString() + '-' + max.toString() + '-' + check+"-"+password);

            if (checkPassword2(password, min, max, check) )
                counter++;

        }


        return counter.toString();
    }


    private boolean checkPassword(String password, Integer min, Integer max, String check) {
        Integer numberOfAppearances=0;
        for(int i=0;i<password.length();i++) {
          if (password.charAt(i) == check.charAt(0)) {
              numberOfAppearances++;
          }
        }
        return ((numberOfAppearances>=min) && (numberOfAppearances<=max));
    }

    private boolean checkPassword2(String password, Integer min, Integer max, String check) {
        Integer nums=0;
        if (min <= password.length()) {
            if (password.charAt(min-1) == check.charAt(0)) nums++;
            //io.logDebug('-' + password.charAt(min-1));
        }
        if (max<=password.length()) {
            if (password.charAt(max-1) == check.charAt(0)) nums++;
            //io.logDebug(password.charAt(max-1));
        }
        return (nums==1);
    }

}
