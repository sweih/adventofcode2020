package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 extends Day {

    public Object part1(List<String> input) {
        Map<Long,Long> mem = new HashMap<>();
        String mask = "";
        for (String line : input) {
            String s[] = line.split("=");
            String param = s[1].trim();
            String cmd = s[0].trim();
            if (cmd.equals( "mask")) {
               mask = param;
            } else {
              Long res = mulitply(mask, LongToBinary(Long.parseLong(param), 36));

              String address = cmd.substring(4);
              address = address.substring(0,address.length()-1);
              mem.put(Long.parseLong(address), res);
            }

        }
        //System.out.println(mem);
        Long result = 0L;
        for (Map.Entry<Long, Long> entry : mem.entrySet()) {
            result += entry.getValue();
        }

        return Long.toString(result);
    }

    public long mulitply(String mask, String parameter) {
        String res = "";
        for (int i=0;i<mask.length();i++) {
            if (mask.substring(i,i+1).equalsIgnoreCase("X")) {
                res = res + parameter.substring(i,i+1);
            } else {
                res = res + mask.substring(i,i+1);
            }
        }
        return Long.parseLong(res,2);
    }

    public String LongToBinary(Long l, int length) {
        String res = Long.toBinaryString(l);
        while(res.length() < 36) {
              res = "0" + res;
        }
        return res;
    }

    public Object part2(List<String> input) {
        Map<Long,Long> mem = new HashMap<>();
        String mask = "";
        for (String line : input) {
            String s[] = line.split("=");
            String param = s[1].trim();
            String cmd = s[0].trim();
            if (cmd.equals( "mask")) {
                mask = param;
            } else {
                Long res = Long.parseLong(param);
                String address = cmd.substring(4);
                address = address.substring(0,address.length()-1);
                Long[] addresses = calcAddresses(mask, address);
                for (Long a: addresses) {
                    mem.put(a, res);
                }
            }

        }
        //System.out.println(mem);
        Long result = 0L;
        for (Map.Entry<Long, Long> entry : mem.entrySet()) {
            result += entry.getValue();
        }

        return Long.toString(result);
    }

    private Long[] calcAddresses(String mask, String address) {
        String mr = LongToBinary(Long.parseLong(address), 36);
        String res = "";
        List<String> l = new ArrayList<>();
        for (int i=0; i<36; i++) {
            if (mask.substring(i,i+1).equals("0")  ) {
                res = res + mr.substring(i,i+1);
            } else if (mask.substring(i,i+1).equals("1")  ) {
                res = res + "1";
            } else {
                res = res + "X";
            }
        }
        l.add(res);
        return combine(l);
    }

    private Long[] combine(List<String> l) {
        List<String> input = l.stream().collect(Collectors.toList());
        List<String> working = new ArrayList<>();
        for (int i=0;i<36;i++) {
            for (String s : input) {
                if (s.substring(i, i + 1).equalsIgnoreCase("X")) {
                    String s1,s2;

                        s1 = s.substring(0, i) + "0" + s.substring(i + 1);
                        s2 = s.substring(0, i) + "1" + s.substring(i + 1);

                    working.add(s1);
                    working.add(s2);
                } else {
                    working.add(s);
                }
            }
            input = working;
            working = new ArrayList<>();
        }
        Long[] res = new Long[input.size()];
        int c=0;
        for (String s: input) {
          res[c] = Long.parseLong(s,2);
          c++;
        }
        return res;
    }

}
