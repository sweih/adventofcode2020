package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.aoc2020.Matrix.Matrix;

import java.util.List;



public class Day11 extends Day {

    Matrix m;
    Matrix w;

    public Object part1(List<String> input) {
        this.m = new Matrix(input, '.');
        this.w = m.copy();

        boolean equals = false;
        while (!equals) {
            for (int i = 1; i < m.rows-1; i++) {
                for (int j = 1; j < m.cols-1; j++) {
                    clearOrOccupy(i, j);
                }
            }
            equals = this.m.equals(this.w);
            this.m = w.copy();
        }
        int i = this.m.countChars('#');
        return Integer.toString(i);
    }

    private void clearOrOccupy(int i, int j) {
        int num = getNoOfOccupiedNeighbours(i,j);
        if ((num==0) && (this.m.data[i][j] == 'L')) {
          this.w.data[i][j] = '#';
        } else if ((this.m.data[i][j] == '#') && (num>=4)) {
            this.w.data[i][j] = 'L';
        }
    }

    private int getNoOfOccupiedNeighbours(int i, int j) {
        int nb = 0;

        for (int k=-1;k<2;k++) {

        }
        if (m.data[i - 1][j - 1] == '#') nb++;
            if (m.data[i - 1][j] == '#') nb++;
            if (m.data[i - 1][j + 1] == '#') nb++;

            if (m.data[i][j-1] == '#') nb++;
            if (m.data[i][j+1] == '#') nb++;

            if (m.data[i + 1][j - 1] == '#') nb++;
            if (m.data[i + 1][j] == '#') nb++;
            if (m.data[i + 1][j + 1] == '#') nb++;

        return nb;
    }



    private boolean isOccupied(int i, int j) {
        boolean r = false;
        if ((i>=0) || (i<m.rows)) {
            if ((j>=0) || (j<m.rows)) {
                r = ( m.data[i][j] == '#');
            }
        }
        return r;
    }

    public Object part2(List<String> input) {
        // implementation of part2 is embarassing. do commit after refactoring.
        this.m = new Matrix(input);
        this.w = m.copy();
        int counter = 0;
        boolean equals = false;
        int offset = 5;
        while (!equals) {

            for (int i = 0; i < m.rows; i++) {
                for (int j = 0; j < m.cols; j++) {
                    if (m.getCharAt(j, i) == '.') {
                        continue;
                    }
                    int res = 0;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            boolean occupied = false;
                            if ((x == 0) && (y == 0)) {
                                continue;
                            }
                            for (int o = 1; o <= offset; o++) {

                                int checkX = j + x * o;
                                int checkY = i + y * o;

                                if ((checkX < 0) || (checkY < 0) || (checkX >= m.cols) || (checkY >= m.rows)) {
                                    continue;
                                }
                                if (m.getCharAt(checkX, checkY) == '#') occupied = true;
                                if (m.getCharAt(checkX, checkY) == 'L') break;

                            }
                            if (occupied) res++;
                        }
                    }
                    if (m.getCharAt(j,i) == 'L') {
                      if (res==0) w.setCharAt(j,i, '#');
                    } else if (m.getCharAt(j,i) == '#') {
                        if (res>=offset) w.setCharAt(j,i, 'L');
                    }
                }
            }
            equals = this.m.equals(this.w);
            this.m = w.copy();
            counter++;
        }
        int c = this.m.countChars('#');
        return Integer.toString(c);
    }






}
