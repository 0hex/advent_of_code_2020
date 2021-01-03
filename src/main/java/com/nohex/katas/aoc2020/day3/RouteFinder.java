package com.nohex.katas.aoc2020.day3;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Operates on routes around a forest map.
 */
class RouteFinder {

  private final ForestLayout map;

  RouteFinder(ForestLayout map) {
    this.map = map;
  }

  long countObstacles(int xSlope, int ySlope) {
    // Calculate the x- and y-factor for the slope.
    double xFactor = (double) xSlope / ySlope;

    // Traverse map until the bottom is hit.
    return IntStream.range(ySlope, map.getHeight())
        // Match y rate to ySlope.
        .filter(y -> y % ySlope == 0)
        // Get the contents of the map.
        .mapToObj(y -> map.getAt((int) Math.floor(y * xFactor), y))
        // If the map is not empty at that point...
        .filter(Optional::isPresent)
        // ... count it.
        .count();
  }
}
