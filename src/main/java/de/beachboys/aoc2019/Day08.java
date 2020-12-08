package de.beachboys.aoc2019;

import de.beachboys.Day;
import de.beachboys.Util;
import org.javatuples.Triplet;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 extends Day {

    int width = 25;
    int height = 6;

    public Object part1(List<String> input) {
        List<String> layers = Util.splitStringBySize(input.stream().collect(Collectors.joining()), width*height);

        long min=1000000;
        int minlayer = 0;
        int counter = 0;
        for (String layer : layers) {
            if (layer.isBlank()) continue;;
            long tmp = Util.countOccurences(layer, '0', 0);
            if (tmp<min) {
                min = tmp;
                minlayer = counter;
            }
            counter++;
        }

        long res1 = Util.countOccurences(layers.get(minlayer), '1',0);
        long res2 = Util.countOccurences(layers.get(minlayer), '2',0);
        return Long.toString(res1 *res2);
    }


    public Object part2(List<String> input) {
    // 0 is black, 1 is white, and 2 is transparent.
        List<String> layers = Util.splitStringBySize(input.stream().collect(Collectors.joining()), width*height);
        layers = layers.stream().filter(x-> !x.isBlank()).collect(Collectors.toList());;

        //layers = Lists.reverse(layers);
        String result = layers.get(layers.size()-1);
        for (int i= layers.size()-2; i>=1; i--) {
              result = multiplyStrings(layers.get(i), result);
        }

        result = result.replaceAll("0", " ");
        for (int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {
                System.out.print(result.charAt(x+(y*width)));
            }
            System.out.println();
        }
        return "";
    }

    public String multiplyStrings(String string1, String string2) {
        String result = "";
        for (int i=0; i<string1.length();i++) {
          if (string1.charAt(i) == '2') {
              result = result + string2.charAt(i);
          } else {
              result = result + string1.charAt(i);
          }
        }
        return result;
    }



}
