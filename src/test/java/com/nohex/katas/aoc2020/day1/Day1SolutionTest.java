package com.nohex.katas.aoc2020.day1;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class Day1SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay1AExample_when_theExpectedResultIsProvided_then_theAnswerIsGiven() {
    final int target = 2020;
    List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
    final List<Integer> expected = Arrays.asList(1721, 299);

    Optional<List<Integer>> result = new NumberGroupFinder().findForSum(target, input);

    assertThat(result)
        .isPresent()
        .get().asList().containsAll(expected);
  }

  @Test
  void given_theDay1AInput_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    // Load number list from file.
    List<Integer> input = getResourceAsStream("input-day_1.txt")
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    final int target = 2020;

    Optional<List<Integer>> result = new NumberGroupFinder().findForSum(target, input);

    assertThat(result).isPresent();

    List<Integer> pair = result.get();
    logger.info(String.format("The numbers are %s",
        pair.stream().map(String::valueOf).collect(Collectors.joining(", "))));
    logger.info(String.format("The answer is %d", pair.stream().reduce(1, (a, b) -> a * b)));
  }


  @Test
  void given_theDay1BExample_when_theExpectedResultIsProvided_then_theAnswerIsGiven() {
    final int target = 2020;
    List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
    final List<Integer> expected = Arrays.asList(979, 366, 675);

    Optional<List<Integer>> result = new NumberGroupFinder().findForSum(target, input, 3);

    assertThat(result)
        .isPresent()
        .get().asList().containsAll(expected);
  }

  @Test
  void given_theDay1BList_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    final int target = 2020;
    final String fileName = "input-day_1.txt";

    // Load number list from file.
    List<Integer> input = Files.readAllLines(
        Paths.get(ClassLoader.getSystemResource(fileName).toURI())
    ).stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    Optional<List<Integer>> result = new NumberGroupFinder().findForSum(target, input, 3);

    assertThat(result).isPresent();

    List<Integer> pair = result.get();
    logger.info("The numbers are " +
        pair.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    logger.info("The answer is " + pair.stream().reduce(1, (a, b) -> a * b));
  }
}
