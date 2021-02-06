package com.nohex.katas.aoc2020.day6;

import java.util.Collection;
import java.util.Iterator;

interface AnswerParser {

  /**
   * Aggregates different characters in a series of strings. Each character only counts once, no
   * matter how many times it appears. A blank line represents a new group, thus the characters are
   * reset and they can be used for counting again.
   *
   * @param input A line with 0 or more characters.
   * @return The number of different characters in each group.
   */
  Collection<Integer> countDistinctByGroup(Iterator<String> input);
}
