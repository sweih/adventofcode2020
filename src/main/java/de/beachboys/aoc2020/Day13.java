package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.Util;
import org.javatuples.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Day13 extends Day {

    public Object part1(List<String> input) {
        long waittime = 1015292;
        long earliest = Long.MAX_VALUE;
        long busid = -1;
        List<String> deps = Util.parseToList(input.get(0), ",");
        for (String dep :deps) {
          if (dep.equals("x")) continue;
          long le = 0;
          long inp = Long.parseLong(dep);
          while (le<waittime) {
              le+=inp;
          }
          long diff = le - waittime;
          if (diff < earliest) {
             earliest = diff;
             busid = inp;
          }
           earliest = Math.min(earliest,diff);
        }
        return (Long.toString(earliest * busid ));

    }

    public Object part2(List<String> input) {
        List<String> deps = Util.parseToList(input.get(0), ",");
        List<Long> numbers = new ArrayList<>();
        List<Long> remainders = new ArrayList<>();
        long cnt = 0;
        for (String dep :deps) {

            if (dep.equals("x")) {

            } else {
               numbers.add(Long.parseLong(dep));
               remainders.add(cnt);
            }
            cnt++;
        }

        long[] nums = numbers.stream().mapToLong(l -> l).toArray();
        long[] rems = remainders.stream().mapToLong(l -> l).toArray();

        //System.out.println(numbers);
        //System.out.println(remainders);

        return Long.toString(CRT(nums,rems));

    }

    public long CRT(long[] number, long[] remainder) {
        //reference: https://medium.com/free-code-camp/how-to-implement-the-chinese-remainder-theorem-in-java-db88a3f1ffe0

        long product = 1;
        for(int i=0; i<number.length; i++ ){
            product *= number[i];
        }

        long[] partialProduct = new long[number.length];
        for(int i=0; i<number.length; i++){
            partialProduct[i] = product/number[i];
        }

        long[] inverse = new long[number.length];
        for (int i=0; i<number.length; i++) {
            inverse[i] = computeInverse(partialProduct[i],number[i]);
        }



        long sum = 0;
        for (int i=0; i<number.length;i++) {
            sum += partialProduct[i] * inverse[i] * remainder[i];
        }

        return product - (sum % product);
    }

    public static long computeInverse(long a, long b){
        long m = b, t, q;
        long x = 0, y = 1;

        if (b == 1)
            return 0;

        // Apply extended Euclid Algorithm
        while (a > 1)
        {
            // q is quotient
            q = a / b;
            t = b;

            // now proceed same as Euclid's algorithm
            b = a % b;a = t;
            t = x;
            x = y - q * x;
            y = t;
        }

        // Make x1 positive
        if (y < 0)
            y += m;

        return y;
    }

}
