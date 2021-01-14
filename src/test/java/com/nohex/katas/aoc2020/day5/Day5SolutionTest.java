package com.nohex.katas.aoc2020.day5;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class Day5SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay5Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    Stream<String> input = getResourceAsStream("day5/example.txt");

    Optional<Integer> maxId = input
        .map(SeatImpl::new)
        .map(SeatImpl::getId)
        .max(Comparator.naturalOrder());

    assertThat(maxId)
        .isPresent()
        .get()
        .isEqualTo(820);
  }

  @Test
  void given_theDay5Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    // Load input from file.
    Stream<String> input = getResourceAsStream("day5/input.txt");

    Optional<Integer> maxId = input
        .map(SeatImpl::new)
        .map(SeatImpl::getId)
        .max(Comparator.naturalOrder());

    assertThat(maxId).isPresent();

    logger.info("The result is " + maxId.get());
  }

}
