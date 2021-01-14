package com.nohex.katas.aoc2020.day5;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class Day5SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay5Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    Stream<String> input = getResourceAsStream("day5/example.txt");

    Optional<Integer> maxId = getSeatIds(input)
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

    Optional<Integer> maxId = getSeatIds(input)
        .max(Comparator.naturalOrder());

    assertThat(maxId).isPresent();

    logger.info("The result is " + maxId.get());
  }

  @Test
  void given_theDay5Input_when_theSeatListIsProduced_then_theMissingSeatIsWhatRemains()
      throws IOException, URISyntaxException {
    // Load input from file.
    Stream<String> input = getResourceAsStream("day5/input.txt");

    // Collect the seat ID list.
    List<Integer> seatIds = getSeatIds(input)
        .sorted()
        .collect(Collectors.toList());

    // Find an ID missing from the list, whose previous and next IDs are in the list.
    int maxRows = 128;
    int maxColumns = 8;
    int numSeats = (maxRows * 8) + maxColumns;
    OptionalInt mySeatId = IntStream.range(0, numSeats)
        .filter(i -> !seatIds.contains(i))
        .filter(i -> seatIds.contains(i - 1))
        .filter(i -> seatIds.contains(i + 1))
        .findFirst();

    assertThat(mySeatId).isPresent();

    logger.info("The result is " + mySeatId.getAsInt());
  }

  private Stream<Integer> getSeatIds(Stream<String> input) {
    return input
        .map(SeatImpl::new)
        .map(SeatImpl::getId);
  }

}
