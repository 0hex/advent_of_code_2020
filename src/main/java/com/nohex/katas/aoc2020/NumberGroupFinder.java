package com.nohex.katas.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Finds groups of numbers.
 */
class NumberGroupFinder {

  /**
   * Finds three numbers from a list whose sum is the supplied target.
   *
   * @param target  The sum of the group of numbers.
   * @param input   The candidates.
   * @param factors How many numbers should be found.
   * @return The numbers whose sum is the target.
   */
  Optional<List<Integer>> findForSum(int target, List<Integer> input, int factors) {
    // Short-circuit for known results.
    if (factors < 2) {
      return Optional.empty();
    }
    if ((input.size() < factors)) {
      return Optional.empty();
    }

    // If 2 factors are requested, use the complement caching method.
    if (factors < 3) {
      return findForSum(target, input);
    } else {
      // Otherwise, for each element, repeat the search for one less element,
      // in a list without the current element.
      for (Integer current : input) {
        // For the current number, find another subset of numbers that provide the remainder of the sum (the complement).
        int complement = target - current;
        List<Integer> sublist = new ArrayList<>(input);
        sublist.remove(current);
        // Find a subset that fits the complement.
        // The subset to analyse is the current list minus the current element.
        Optional<List<Integer>> potentialSubset = findForSum(complement, sublist, factors - 1);

        // If a suitable subset is found, append the current element.
        if (potentialSubset.isPresent()) {
          List<Integer> subset = potentialSubset.get();
          subset.add(current);
          return Optional.of(subset);
        }
      }
    }

    // No suitable results were found.
    return Optional.empty();
  }

  /**
   * Finds a pair of numbers whose sum is the supplied target.
   *
   * @param target The sum of the pair of numbers.
   * @return The pair of numbers whose sum is the target.
   */
  Optional<List<Integer>> findForSum(int target, List<Integer> input) {
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
        return Optional.of(Stream.of(
            current,
            complements.get(current)
        ).collect(Collectors.toList()));
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
