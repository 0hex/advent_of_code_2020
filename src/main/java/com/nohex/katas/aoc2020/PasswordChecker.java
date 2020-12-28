package com.nohex.katas.aoc2020;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

  /**
   * Counts how many valid passwords are contained in the input, according the their respective
   * policies
   * <p>
   * The input consists of a list where each element contains an encoded {@link Policy}, a
   * semi-colon, a space and a password.
   *
   * @param input The passwords and their policies.
   * @return How many of the supplied passwords are correct according to their policies.
   */
  public int countValidPasswords(List<String> input) {
    int validPasswordCount = 0;
    for (String passwordAndPolicy : input) {
      String[] parts = passwordAndPolicy.split(": ");
      String policySource = parts[0];
      String password = parts[1];
      // Check the password against the policy.
      if (new Policy(policySource).isValid(password)) {
        // If the password is valid, increase the counter.
        validPasswordCount++;
      }
    }

    return validPasswordCount;
  }

  static class Policy {

    private static final Pattern pattern = Pattern.compile("(\\d+)-(\\d+) (\\w)");
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
    Policy(String encodedPolicy) {
      // Parse policy
      Matcher matcher = pattern.matcher(encodedPolicy);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("The policy could not be understood: " + encodedPolicy);
      }

      min = Integer.parseInt(matcher.group(1));
      max = Integer.parseInt(matcher.group(2));
      letter = matcher.group(3).charAt(0);
    }

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
}
