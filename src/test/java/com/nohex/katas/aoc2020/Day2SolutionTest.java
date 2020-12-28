package com.nohex.katas.aoc2020;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class Day2SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay2Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven() {
    List<String> input = new ArrayList<>();
    input.add("1-3 a: abcde");
    input.add("1-3 b: cdefg");
    input.add("2-9 c: ccccccccc");

    PasswordChecker sut = new PasswordChecker();

    assertThat(sut.countValidPasswords(input)).isEqualTo(2);
  }
}
