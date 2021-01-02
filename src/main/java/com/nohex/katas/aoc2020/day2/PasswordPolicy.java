package com.nohex.katas.aoc2020.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordPolicy {

  private static final Pattern pattern = Pattern.compile("(\\d+)-(\\d+) ([a-z])");
  private final int first;
  private final int last;
  private final char letter;

  /**
   * Creates a password policy from its string representation.
   * <p>
   * The string is composed of a number, a dash, another number, a space and a letter. The first
   * number is the minimum times the letter must appear, and the second is the maximum thereof.
   *
   * @param encodedPolicy The policy string representation.
   */
  PasswordPolicy(String encodedPolicy) {
    // Parse policy
    Matcher matcher = pattern.matcher(encodedPolicy);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("The policy could not be understood: " + encodedPolicy);
    }

    first = Integer.parseInt(matcher.group(1)) - 1;
    if (first < 0) {
      throw new IllegalArgumentException("First position is less than 1");
    }

    last = Integer.parseInt(matcher.group(2)) - 1;
    if (last < first) {
      throw new IllegalArgumentException("Last position is earlier than the first");
    }

    letter = matcher.group(3).charAt(0);
  }

  /**
   * Checks whether a password is valid against the current policy.
   *
   * @param password The password to check.
   * @return Whether the password is valid.
   */
  boolean isValid(String password) {
    boolean firstHit = password.charAt(first) == letter;
    boolean lastHit = password.charAt(last) == letter;
    return firstHit ^ lastHit;
  }
}
