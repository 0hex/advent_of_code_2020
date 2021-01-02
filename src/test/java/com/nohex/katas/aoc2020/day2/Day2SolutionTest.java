package com.nohex.katas.aoc2020.day2;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import com.nohex.katas.aoc2020.day2.PasswordChecker;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class Day2SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay2Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven() {
    Stream<String> input = Stream.of(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc"
    );

    PasswordChecker sut = new PasswordChecker();

    assertThat(sut.countValidPasswords(input)).isEqualTo(1L);
  }

  @Test
  void given_theDay2Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws URISyntaxException, IOException {
    // Load input from file.
    Stream<String> input = getResourceAsStream("input-day_2.txt");

    long count = new PasswordChecker().countValidPasswords(input);

    assertThat(count).isPositive();

    logger.info("The number of valid passwords is " + count);
  }
}
