package de.beachboys.aoc2020.Matrix;

public abstract class MatrixTraverser {

    public int cols = 0;
    public int rows = 0;

    public int cursorX = 0;
    public int cursorY = 0;

    public Matrix matrix;

    public abstract char nextChar();
    public abstract boolean next();

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        this.cols = this.matrix.cols;
        this.rows = this.matrix.rows;
    }

}
