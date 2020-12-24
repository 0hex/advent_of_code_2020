package com.nohex.katas.aoc2020;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class PairFinderTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_aListOfNumbers_when_theSumOfTwoOfThemIsProvided_then_aPairOfNumbersIsFound() {
    List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
    // The indexes of the numbers to sum.
    int firstExpectedElement = 3;
    int secondExpectedElement = 5;
    // The actual numbers that will be summed.
    int firstExpectedNumber = input.get(firstExpectedElement);
    int secondExpectedNumber = input.get(secondExpectedElement);

    // Provide the sum of two elements to ensure a successful outcome.
    final int sum = firstExpectedNumber + secondExpectedNumber;
    Optional<Integer[]> actual = new PairFinder().findForSum(sum, input);

    assertThat(actual)
        .isPresent()
        .get().isEqualTo(new Integer[]{firstExpectedNumber, secondExpectedNumber});
  }

  @Test
  void given_aListOfTwoNumbers_when_theSumOfBothIsProvided_then_aPairOfNumbersIsFound() {
    final int firstNumber = 3;
    final int secondNumber = 14;
    List<Integer> input = Arrays.asList(firstNumber, secondNumber);

    // Provide a sum other than that of the elements, to set up the failure.
    final int sum = firstNumber + secondNumber;
    Optional<Integer[]> actual = new PairFinder().findForSum(sum, input);

    assertThat(actual)
        .isPresent()
        .get().isEqualTo(new Integer[]{firstNumber, secondNumber});
  }

  @Test
  void given_aListOfTwoNumbers_when_theSumOfBothIsNotProvided_then_aPairOfNumbersIsNotFound() {
    List<Integer> input = Arrays.asList(1, 2);

    // Provide a sum other than that of the elements, to set up the failure.
    final int sumPlusOne = input.stream().mapToInt(a -> a).sum() + 1;
    Optional<Integer[]> actual = new PairFinder().findForSum(sumPlusOne, input);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_aListWithOneElement_when_anySumIsProvided_then_aPairOfNumbersIsNotFound() {
    final int theNumber = 1;
    List<Integer> input = Collections.singletonList(theNumber);

    Optional<Integer[]> actual = new PairFinder().findForSum(theNumber, input);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_anEmtpyList_when_anySumIsProvided_then_aPairOfNumbersIsNotFound() {
    Optional<Integer[]> actual = new PairFinder().findForSum(0, Collections.emptyList());

    assertThat(actual).isEmpty();
  }

  @Test
  void given_theDay1List_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws IOException, URISyntaxException {
    final int target = 2020;
    final String fileName = "input-day_1.txt";

    // Load number list from file.
    List<Integer> input = Files.readAllLines(
        Paths.get(ClassLoader.getSystemResource(fileName).toURI())
    ).stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    Optional<Integer[]> result = new PairFinder().findForSum(target, input);

    assertThat(result).isPresent();

    Integer[] pair = result.get();
    logger.info(String.format("The numbers are %d and %d", pair[0], pair[1]));
    logger.info(String.format("The answer is %d", pair[0] * pair[1]));
  }
}