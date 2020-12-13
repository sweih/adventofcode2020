package de.beachboys.aoc2020;

import de.beachboys.Day;
import java.util.List;


public class Day12 extends Day {

    public Object part1(List<String> input) {

        int direction = 0;
        int dx = 0;
        int dy = 0;
        boolean move;

        for (String line: input) {
            String cmd = line.substring(0,1);
            Integer par = Integer.parseInt(line.substring(1));
            move = false;

            switch (cmd) {
                case "E":
                    dx+=par;
                    break;
                case "S":
                    dy+=par;
                    break;
                case "W":
                    dx-=par;
                    break;
                case "N":
                    dy-=par;
                    break;
                case "F":
                    move = true;
                    break;
                case "R":
                    direction = (((direction * 90) + par) / 90) % 4;
                    break;
                case "L":
                    direction = (((direction * 90) - par) / 90) % 4;
                    break;
                default:
                    break;
            }
            if (direction == -1) direction = 3;
            if (direction == -2) direction = 2;
            if (direction == -3) direction = 1;

            if (move) {
                if (direction == 0) dx+=par;
                if (direction == 1) dy+=par;
                if (direction == 2) dx-=par;
                if (direction == 3) dy-=par;
            }


        }
        System.out.println(dx + ";" + dy+ ';' + direction);
        return Integer.toString(Math.abs(dx)+Math.abs(dy));


    }

    public Object part2(List<String> input) {

        String[] directions = {"E", "S", "W", "N"};
        int rs = 0;
        long dx = 10; // Waypoint x
        long dy = -1; // WPoint y
        long px = 0;
        long py = 0;
        boolean move;

        for (String line: input) {
            String cmd = line.substring(0,1);
            Integer par = Integer.parseInt(line.substring(1));
            move = false;

            switch (cmd) {
                case "E":
                    dx+=par;
                    break;
                case "S":
                    dy+=par;
                    break;
                case "W":
                    dx-=par;
                    break;
                case "N":
                    dy-=par;
                    break;
                case "F":
                    move = true;
                    break;
                case "R":
                    rs = (par / 90);
                    for (int i=0;i<rs;i++) {
                        long tx=dx;
                        dx = -1 * dy;
                        dy =  tx;
                    }

                    break;
                case "L":
                    rs = ( par / 90);
                    for (int i=0;i<rs;i++) {
                        long tx=dx;
                        dx =  dy;
                        dy = -1 * tx;
                    }
                    break;
                default:
                    break;
            }
            if (move) {
                px = px + (par * dx);
                py = py + (par * dy);
            }


        }
        return Long.toString(Math.abs(px)+Math.abs(py));

    }

}

