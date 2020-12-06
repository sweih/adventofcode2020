package de.beachboys.aoc2020.Matrix;

import de.beachboys.IOHelper;
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {

    public int cols = 0;
    public int rows = 0;

    private char[][] data;

    public Matrix(List<String> input) {
        this.parseInputToMatrix(input);
    }


    public void parseInputToMatrix(List<String> input) {
      this.rows = input.size();
      this.cols = input.get(0).length();
      this.data = new char[this.rows][this.cols];

      int y = 0;
      int x = 0;

      for (String row: input) {
          x = 0;
          for (int i = 0;i<this.cols; i++) {
            this.data[y][x] = row.charAt(i);
            x++;
          }
          y++;
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

    public char getCharAt(Integer x, Integer y) {
        return this.data[y][x];
    }

    public void setCharAt(Integer x, Integer y, char c) {
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
