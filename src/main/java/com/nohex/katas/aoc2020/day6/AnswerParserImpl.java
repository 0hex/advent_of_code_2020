package com.nohex.katas.aoc2020.day6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class AnswerParserImpl implements AnswerParser {

  @Override
  public Collection<Integer> countDistinctByGroup(Iterator<String> input) {
    Collection<Integer> groupCounts = new ArrayList<>();
    int groupCount = 0;
    Set<Character> groupLetters = new HashSet<>();

    while (input.hasNext()) {
      String line = input.next();
      if (line.isBlank()) {
        // If the line is blank, add the current count
        // as the group's count, and start a new group.
        groupCounts.add(groupCount);
        groupCount = 0;
        groupLetters.clear();
      } else {
        // Otherwise, count the different letters.
        for (int i = 0; i < line.length(); i++) {
          char letter = line.charAt(i);
          if (!groupLetters.contains(letter)) {
            groupCount++;
            groupLetters.add(letter);
          }
        }
      }
    }

    // Ensure that the last group is also counted.
    if (groupCount > 0) {
      groupCounts.add(groupCount);
    }

    return groupCounts;
  }
}
