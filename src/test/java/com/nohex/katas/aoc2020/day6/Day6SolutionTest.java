package com.nohex.katas.aoc2020.day6;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class Day6SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay6Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    Stream<String> input = getResourceAsStream("day6/example.txt");

    AnswerParser sut = new AnswerParserImpl();
    Collection<Integer> counts = sut.countDistinctByGroup(input.iterator());

    assertThat(counts.stream().mapToInt(i -> i).sum())
        .isEqualTo(11);
  }

  @Test
  void given_theDay6Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    // Load input from file.
    Stream<String> input = getResourceAsStream("day6/input.txt");

    AnswerParser sut = new AnswerParserImpl();
    Collection<Integer> counts = sut.countDistinctByGroup(input.iterator());

    final int result = counts.stream().mapToInt(i -> i).sum();
    assertThat(result).isPositive();

    logger.info("The result is " + result);
  }
}
