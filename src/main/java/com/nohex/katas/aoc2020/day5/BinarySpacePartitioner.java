package com.nohex.katas.aoc2020.day5;

import java.util.List;

/**
 * Provides methods to split collections of elements using binar space partitioning.
 */
class BinarySpacePartitioner {

  /**
   * Finds out a number following the binary space partition of the set of integers needed to trace
   * all the steps, i.e. 2^(steps - 1). Please take into account that the returned number is
   * 0-based, so for 8 steps it will return a number between 0 and 127.
   *
   * @param steps Instructions on how to reduce by taking either the lower or the upper half.
   * @return The resulting number, 0-based.
   */
  int intReduce(List<BinarySpace> steps) {
    int elementsPerHalf = 2 << (steps.size() - 1);
    int base = 0;
    for (BinarySpace step : steps) {
      elementsPerHalf /= 2;
      int middle = base + elementsPerHalf;

      // If the upper half is required, the base is now the middle.
      if (step.equals(BinarySpace.UPPER)) {
        base = middle;
      }
    }

    return base;
  }

  enum BinarySpace {
    UPPER,
    LOWER
  }

}
