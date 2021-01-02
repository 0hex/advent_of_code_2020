package com.nohex.katas.aoc2020.day1;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.day1.NumberGroupFinder;
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

class NumberGroupFinderTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_aListOfNumbers_when_theSumOfTwoOfThemIsProvided_then_aPairOfNumbersIsFound() {
    List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
    // The indexes of the numbers to sum.
    List<Integer> expected = Arrays.asList(input.get(3), input.get(5));

    // Provide the sum of two elements to ensure a successful outcome.
    final int sum = expected.stream().mapToInt(i -> i).sum();
    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(sum, input);

    assertThat(actual)
        .isPresent()
        .get().asList().containsAll(expected);
  }

  @Test
  void given_aListOfTwoNumbers_when_theSumOfBothIsProvided_then_aPairOfNumbersIsFound() {
    final int firstNumber = 3;
    final int secondNumber = 14;
    List<Integer> input = Arrays.asList(firstNumber, secondNumber);

    // Provide a sum other than that of the elements, to set up the failure.
    final int sum = firstNumber + secondNumber;
    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(sum, input);

    assertThat(actual)
        .isPresent()
        .get().asList().containsAll(input);
  }

  @Test
  void given_aListOfTwoNumbers_when_theSumOfBothIsNotProvided_then_aPairOfNumbersIsNotFound() {
    List<Integer> input = Arrays.asList(1, 2);

    // Provide a sum other than that of the elements, to set up the failure.
    final int sumPlusOne = input.stream().mapToInt(a -> a).sum() + 1;
    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(sumPlusOne, input);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_aListWithOneElement_when_anySumIsProvided_then_aPairOfNumbersIsNotFound() {
    final int theNumber = 1;
    List<Integer> input = Collections.singletonList(theNumber);

    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(theNumber, input);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_anEmptyList_when_anySumIsProvided_then_aPairOfNumbersIsNotFound() {
    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(0, Collections.emptyList());

    assertThat(actual).isEmpty();
  }

  @Test
  void given_aListOfNumbers_when_theSumOfThreeOfThemIsProvided_then_aTrioIsFound() {
    List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
    List<Integer> expectedElements = Arrays.asList(
        input.get(1), input.get(3), input.get(5)
    );

    // Provide the sum of three elements to ensure a successful outcome.
    final int sum = expectedElements.stream().mapToInt(i -> i).sum();
    Optional<List<Integer>> actual = new NumberGroupFinder()
        .findForSum(sum, input, expectedElements.size());

    assertThat(actual)
        .isPresent()
        .get().asList().containsAll(expectedElements);
  }

  @Test
  void given_aListOfThreeNumbers_when_theirSumIsProvided_then_theTrioIsFound() {
    List<Integer> input = Arrays.asList(3, 14, 15);

    // Provide a sum other than that of the elements, to set up the failure.
    final int sum = input.stream().mapToInt(i -> i).sum();
    Optional<List<Integer>> actual = new NumberGroupFinder()
        .findForSum(sum, input, input.size());

    assertThat(actual)
        .isPresent()
        .get().asList().containsAll(input);
  }

  @Test
  void given_aListOfThreeNumbers_when_theirSumIsNotProvided_then_aTrioIsNotFound() {
    List<Integer> input = Arrays.asList(1, 2, 3);

    // Provide a sum other than that of the elements, to set up the failure.
    final int sumPlusOne = input.stream().mapToInt(a -> a).sum() + 1;
    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(sumPlusOne, input, 3);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_aListWithLessThanThreeElements_when_theirSumIsProvided_then_aTrioIsNotFound() {
    List<Integer> input = Arrays.asList(1, 2);

    final int sum = input.stream().mapToInt(i -> i).sum();
    Optional<List<Integer>> actual = new NumberGroupFinder().findForSum(sum, input, 3);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_anEmptyList_when_aZeroSumIsProvided_then_aTrioIsNotFound() {
    Optional<List<Integer>> actual = new NumberGroupFinder()
        .findForSum(0, Collections.emptyList(), 3);

    assertThat(actual).isEmpty();
  }

  @Test
  void given_theDay1Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven() {
    final int target = 2020;
    List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
    final List<Integer> expected = Arrays.asList(979, 366, 675);

    Optional<List<Integer>> result = new NumberGroupFinder().findForSum(target, input, 3);

    assertThat(result)
        .isPresent()
        .get().asList().containsAll(expected);
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

    Optional<List<Integer>> result = new NumberGroupFinder().findForSum(target, input, 3);

    assertThat(result).isPresent();

    List<Integer> pair = result.get();
    logger.info("The numbers are " +
        pair.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    logger.info("The answer is " + pair.stream().reduce(1, (a, b) -> a * b));
  }
}