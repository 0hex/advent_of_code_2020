package com.nohex.katas.aoc2020.day4;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class Day4SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay4Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException {
    Reader input = getResourceAsReader("example-day_4.txt");

    List<Passport> validPassports = PassportFactory.parse(input);

    assertThat(validPassports.size()).isEqualTo(2);
  }

  @Test
  void given_theDay4Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException {
    // Load input from file.
    Reader input = getResourceAsReader("input-day_4.txt");

    List<Passport> passports = PassportFactory.parse(input);

    int count = passports.size();

    assertThat(count).isPositive();

    logger.info("The result is " + count);
  }
}
