package de.beachboys.aoc2020.Matrix;


enum BounceDirection {
    FORWARD,
    BACKWARD
}

public class BounceDirectionRef {

  public BounceDirection bd;

  public BounceDirectionRef(BounceDirection bd) {
    this.bd = bd;
  }

}
