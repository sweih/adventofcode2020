package de.beachboys.aoc2020;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RParser {

    Pattern pattern;

    public RParser(String pattern) {
      this.pattern = Pattern.compile(pattern);
    }


    public List<List<Integer>> parseToIntegerList(List<String> input, int items, boolean throwsException) {
        List<List<Integer>> result = new ArrayList<>();

        for (String line: input) {
            Matcher m = this.pattern.matcher(line);
            if (m.find()) {
                if (m.groupCount() == items) {
                    List<Integer> ar = new ArrayList<>();

                    for (int i=1; i <= items; i++) {
                        ar.add(Integer.parseInt( m.group(i) ));
                    }

                    result.add(ar);
                } else {
                    if (throwsException)
                        throw new IllegalStateException("Wrong number of matches: Was " + m.groupCount() + " but wanted " + items);
                }
            } else {
                if (throwsException)
                    throw new IllegalStateException("Pattern did not match.");
            }
        }
        return result;
    }


    public List<List<String>> parseToStringList(List<String> input, int items, boolean throwsException) {
        List<List<String>> result = new ArrayList<>();

        for (String line: input) {
            Matcher m = this.pattern.matcher(line);
            if (m.find()) {
                if (m.groupCount() == items) {
                    List<String> ar = new ArrayList<>();

                    for (int i=1; i <= items; i++) {
                        ar.add(m.group(i));
                    }

                    result.add(ar);
                } else {
                    if (throwsException)
                        throw new IllegalStateException("Wrong number of matches: Was " + m.groupCount() + " but wanted " + items);
                }
            } else {
                if (throwsException)
                    throw new IllegalStateException("Pattern did not match.");
            }
        }
        return result;
    }


    public List<Integer[]> parseToIntegerArrayList(List<String> input, int items, boolean throwsException) {
        List<Integer[]> result = new ArrayList<>();

        for (String line: input) {
            Matcher m = this.pattern.matcher(line);
            if (m.find()) {
                if (m.groupCount() == items) {
                    Integer[] ar = new Integer[items];

                    for (int i=1; i <= items; i++) {
                        ar[i-1] = Integer.parseInt( m.group(i) );
                    }

                    result.add(ar);
                } else {
                    if (throwsException)
                        throw new IllegalStateException("Wrong number of matches: Was " + m.groupCount() + " but wanted " + items);
                }
            } else {
                if (throwsException)
                    throw new IllegalStateException("Pattern did not match.");
            }
        }
        return result;
    }


    public List<String[]> parseToStringArrayList(List<String> input, int items, boolean throwsException) {
        List<String[]> result = new ArrayList<>();

        for (String line: input) {
            Matcher m = this.pattern.matcher(line);
            if (m.find()) {
                if (m.groupCount() == items) {
                  String[] ar = new String[items];

                  for (int i=1; i<=items; i++) {
                    ar[i-1] = (String) m.group(i);
                  }

                  result.add(ar);
                } else {
                    if (throwsException)
                      throw new IllegalStateException("Wrong number of matches: Was " + m.groupCount() + " but wanted " + items);
                }
            } else {
                if (throwsException)
                  throw new IllegalStateException("Pattern did not match.");
            }
        }
        return result;
    }


}
