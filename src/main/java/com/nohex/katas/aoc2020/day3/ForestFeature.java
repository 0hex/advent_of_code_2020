package com.nohex.katas.aoc2020.day3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Features on a layout representing a forest.
 */
enum ForestFeature {
  BLANK('.'),
  TREE('#');

  private final char symbol;

  private static final Map<Character, ForestFeature> lookup = new HashMap<>();

  static {
    for (ForestFeature forestFeature : ForestFeature.values()) {
      lookup.put(forestFeature.getSymbol(), forestFeature);
    }
  }

  static Optional<ForestFeature> fromSymbol(char symbol) {
    return lookup.containsKey(symbol)
        ? Optional.of(lookup.get(symbol))
        : Optional.empty();
  }

  ForestFeature(char symbol) {
    this.symbol = symbol;
  }

  char getSymbol() {
    return symbol;
  }
}
