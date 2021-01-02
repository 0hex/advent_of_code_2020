package com.nohex.katas.aoc2020.day3;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Operates on routes around a forest map.
 */
class RouteFinder {

  // Proportion of x per each y.
  private static final int X_FACTOR = 3;
  private final ForestLayout map;

  RouteFinder(ForestLayout map) {
    this.map = map;
  }

  long countObstacles() {
    // Traverse map until the bottom is hit.
    return IntStream.range(0, map.getHeight())
        // Get the contents of the map.
        .mapToObj(y -> map.getAt(y * X_FACTOR, y))
        // If the map is not empty at that point...
        .filter(Optional::isPresent)
        // ... count it.
        .count();
  }
}
