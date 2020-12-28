package com.nohex.katas.aoc2020;

import java.util.stream.Stream;

public class PasswordChecker {

  /**
   * Counts how many valid passwords are contained in the input, according the their respective
   * policies
   * <p>
   * The input consists of a list where each element contains an encoded {@link PasswordPolicy}, a
   * semi-colon, a space and a password.
   *
   * @param input The passwords and their policies.
   * @return How many of the supplied passwords are correct according to their policies.
   */
  public long countValidPasswords(Stream<String> input) {
    return input
        .filter(this::checkPasswordAgainstPolicy)
        .count();
  }

  /**
   * Checks whether a password is valid against its policy.
   * <p>
   * The input is provided as an encoded {@link PasswordPolicy}, a semi-colon, a space and a
   * password.
   *
   * @param passwordAndPolicy The encoded password and policy.
   * @return Whether the password is valid according to the policy.
   */
  private boolean checkPasswordAgainstPolicy(String passwordAndPolicy) {
    String[] parts = passwordAndPolicy.split(": ");
    String encodedPolicy = parts[0];
    String password = parts[1];
    // Check the password against the policy.
    return new PasswordPolicy(encodedPolicy).isValid(password);
  }
}
