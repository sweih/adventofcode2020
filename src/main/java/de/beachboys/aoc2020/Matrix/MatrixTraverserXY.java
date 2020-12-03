package de.beachboys.aoc2020.Matrix;

public class MatrixTraverserXY extends MatrixTraverser {

    private int stepX=0;
    private int stepY=0;

    private int lastX=0;
    private int lastY=0;

    public OverflowMode modeX = OverflowMode.MODULO;
    public OverflowMode modeY = OverflowMode.NONE;
    public BounceDirectionRef bdX = new BounceDirectionRef(BounceDirection.FORWARD);
    public BounceDirectionRef bdY = new BounceDirectionRef(BounceDirection.FORWARD);


    public MatrixTraverserXY(int stepX, int stepY) {
      this.stepX = stepX;
      this.stepY = stepY;
    }


    public void setCursor(int positionX, int positionY) {
        this.cursorX = positionX;
        this.cursorY = positionY;
    }

    public void reset() {
        this.cursorX = 0;
        this.cursorY = 0;
    }


    @Override
    public char nextChar() {
       char result = this.matrix.getCharAt(this.cursorX, this.cursorY);

       this.cursorX = this.recalculatePosition(this.cursorX, this.stepX, this.cols, this.bdX, this.modeX);
       this.cursorY = this.recalculatePosition(this.cursorY, this.stepY, this.rows, this.bdY, this.modeY);

       return result;
    }

    private int recalculatePosition(int position, int step, int maximum, BounceDirectionRef bd,  OverflowMode mode) throws IllegalStateException {
        switch (mode) {
            case NONE:
                return position + step;
            case MODULO:
                return (position + step) % maximum;
            case BOUNCE:
                if (bd.bd == BounceDirection.FORWARD) {
                    if ((position + step) < maximum) {
                        return (position + step);
                    } else {
                      bd.bd = BounceDirection.BACKWARD;
                      return ((maximum-1) - ((position + step) - (maximum-1)));
                    }
                } else {
                    if ((position - step) >= 0) {
                        return (position - step);
                    } else {
                        bd.bd = BounceDirection.FORWARD;
                        return Math.abs(position - step);
                    }
                }
            default:
                throw new IllegalStateException("Unknown BounceMode");
        }
    }


    @Override
    public boolean next() {
        switch (this.modeY) {
            case NONE:
                return ((this.cursorY + this.stepY) <= this.rows);
            default:
                return true;
        }
    }

    public void mirror() {

        this.cursorX = this.lastX;
        this.cursorY = this.lastY;

        if (this.bdX.bd == BounceDirection.FORWARD) {
            this.bdX.bd = BounceDirection.BACKWARD;
        } else {
            this.bdX.bd = BounceDirection.FORWARD;
        }

        if (this.bdY.bd == BounceDirection.FORWARD) {
            this.bdY.bd = BounceDirection.BACKWARD;
        } else {
            this.bdY.bd = BounceDirection.FORWARD;
        }
    }
}
