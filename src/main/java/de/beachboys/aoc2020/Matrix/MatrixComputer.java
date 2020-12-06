package de.beachboys.aoc2020.Matrix;

import de.beachboys.IOHelper;

import java.util.List;

public class MatrixComputer {

    public Matrix matrix;
    public MatrixTraverser traverser;

    public Integer lastX = 0;
    public Integer lastY = 0;

    public MatrixComputer(List<String> input, MatrixTraverser traverser) {
        this.matrix = new Matrix(input);
        this.setTraverser(traverser);
    }

    public char nextChar() {
      return this.traverser.nextChar();
    }

    public void replaceCurrentChar(char c) {
        this.matrix.setCharAt(this.lastX,this.lastY,c);
    }

    public boolean next() {
        this.lastX = this.traverser.cursorX;
        this.lastY = this.traverser.cursorY;
        return this.traverser.next();
    }

    public void setTraverser(MatrixTraverser traverser) {
        this.traverser = traverser;
        this.traverser.setMatrix(this.matrix);
    }

}
