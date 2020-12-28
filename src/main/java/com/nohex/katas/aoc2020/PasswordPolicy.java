package com.nohex.katas.aoc2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordPolicy {

  private static final Pattern pattern = Pattern.compile("(\\d+)-(\\d+) ([a-z])");
  private final int max;
  private final int min;
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

    min = Integer.parseInt(matcher.group(1));
    max = Integer.parseInt(matcher.group(2));
    if (max < min) {
      throw new IllegalArgumentException(
          "The maximum cannot be smaller than the minimum: " + encodedPolicy);
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
    int letterCount = 0;
    for (char currentLetter : password.toCharArray()) {
      if (currentLetter == letter) {
        letterCount++;
        // Short circuit.
        if (letterCount > max) {
          return false;
        }
      }
    }

    return letterCount >= min;
  }
}
