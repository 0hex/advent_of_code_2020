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
    Reader input = getResourceAsReader("day4/example.txt");

    List<Passport> validPassports = PassportFactory.parse(input);

    assertThat(validPassports.size()).isEqualTo(2);
  }

  @Test
  void given_theDay4Part2Example_when_invalidDataIsProvided_then_noPassportsAreAccepted()
      throws IOException {
    Reader input = getResourceAsReader("day4/example-invalid_passports.txt");

    List<Passport> validPassports = PassportFactory.parse(input);

    assertThat(validPassports.size()).isZero();
  }

  @Test
  void given_theDay4Part2Example_when_validDataIsProvided_then_allPassportsAreAccepted()
      throws IOException {
    Reader input = getResourceAsReader("day4/example-valid_passports.txt");

    List<Passport> validPassports = PassportFactory.parse(input);

    assertThat(validPassports.size()).isEqualTo(4);
  }

  @Test
  void given_theDay4Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException {
    // Load input.txt from file.
    Reader input = getResourceAsReader("day4/input.txt");

    List<Passport> passports = PassportFactory.parse(input);

    int count = passports.size();

    assertThat(count).isPositive();

    logger.info("The result is " + count);
  }

}
