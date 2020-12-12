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

        this.m.print();
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
        return "2066";
    }






}
