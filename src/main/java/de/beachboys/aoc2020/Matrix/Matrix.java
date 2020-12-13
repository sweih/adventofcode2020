package de.beachboys.aoc2020.Matrix;

import de.beachboys.IOHelper;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {

    public int cols = 0;
    public int rows = 0;

    public char[][] data;
    public char surroundWith = 0x00;

    public Matrix(List<String> input) {
        this.parseInputToMatrix(input);
    }
    public Matrix(List<String> input, char surroundWith) {
        this.surroundWith = surroundWith;
        this.parseInputToMatrix(input);
    }


    public void parseInputToMatrix(List<String> input) {
        int offset = 0;
        if (this.surroundWith != 0x00) offset = 2;

        this.rows = input.size() + offset;
        this.cols = input.get(0).length() + offset;
        this.data = new char[this.rows][this.cols];

        int origCols = input.get(0).length();
        int origRows = input.size();

        int y = 0;
        int x = 0;
        if (offset>0) {
            for (int i = 0; i < this.cols; i++) {
                this.data[0][i] = this.surroundWith;
            }
            y=1;
        }

      for (String row: input) {

          x=0;
          if (offset>0) {
              this.data[y][x] = this.surroundWith;
              x = 1;
          }

          for (int i = 0;i<origCols; i++) {
              this.data[y][x] = row.charAt(i);
            x++;
          }
          if (offset>0) {
              this.data[y][x] = this.surroundWith;
          }
          y++;
      }

        if (offset>0) {
            for (int i = 0; i < this.cols; i++) {
                this.data[y][i] = this.surroundWith;
            }
        }
    }

    public void print() {
        for (int y=0; y<this.rows; y++) {
            for (int x=0; x<this.cols;x++) {
                System.out.print(this.data[y][x]);
            }
            System.out.println("");
        }
    }

    public List<String> toList() {
      List<String> l = new ArrayList<>();

        for (int i=0; i< rows; i++) {
          String s = "";
          for (int j=0;j<cols;j++) {
              s = s + this.data[i][j];
          }
          l.add(s);
      }
        return l;
    }

    public Matrix copy() {
        Matrix n = new Matrix(this.toList());
        return n;
    }

    public char getCharAt(int x, int y) {
        return this.data[y][x];
    }

    public void setCharAt(int x, int y, char c) {
        this.data[y][x] = c;
    }

    public Set<Pair<Integer,Integer>> findChars(char c) {
        Set<Pair<Integer, Integer>> result = new HashSet<>();
        for (int y=0; y<this.rows; y++) {
          for (int x=0; x<this.cols; x++) {
              if (this.data[y][x] == c) {
                result.add(new Pair(y,x));
              }
          }
        }
        return result;
    }

    public boolean equals(Matrix n) {
        boolean result = true;
        for (int y=0; y<this.rows; y++) {
            for (int x = 0; x < this.cols; x++) {
              result = result && (this.data[y][x] == n.data[y][x]);
            }
        }
        return result;
    }

    public Integer countChars(char c) {
        Integer count = 0;
        for (int y=0; y<this.rows; y++) {
            for (int x=0; x<this.cols; x++) {
                if (this.data[y][x] == c) {
                    count++;
                }
            }
        }
        return count;
    }



}
