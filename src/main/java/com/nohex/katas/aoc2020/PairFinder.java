package com.nohex.katas.aoc2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Finds pairs of numbers.
 */
class PairFinder {

  /**
   * Finds a pair of numbers whose sum is the supplied target.
   *
   * @param target The sum of the pair of numbers.
   * @return The pair of numbers whose sum is the target.
   */
  Optional<Integer[]> findForSum(int target, List<Integer> input) {
    // Short-circuit for useless attempts.
    if (input.size() < 2) {
      return Optional.empty();
    }

    // A map that stores a number and its complement
    // (the number the current one needs to get to the target)
    Map<Integer, Integer> complements = new HashMap<>();
    for (int current : input) {

      if (complements.containsKey(current)) {
        // Found the number, which means it's the complement
        // of another number in the list. Return both.
        return Optional.of(
            // Return the numbers in the order they were visited.
            new Integer[]{complements.get(current), current}
        );
      } else {
        // The current number does not have a recorded complement.
        // Store its complement, along with the current number.
        int complement = target - current;
        complements.put(complement, current);
      }
    }

    return Optional.empty();
  }
}
