package com.nohex.katas.aoc2020.day3;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.SolutionTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class Day3SolutionTest extends SolutionTest {

  private static final Logger logger = Logger.getAnonymousLogger();

  @Test
  void given_theDay3Example_when_theExpectedResultIsProvided_then_theAnswerIsGiven() {
    Stream<String> input = Stream.of(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"
    );

    ForestLayout map = new ForestLayout(input);
    RouteFinder sut = new RouteFinder(map);

    assertThat(sut.countObstacles(1, 1)).isEqualTo(2);
    assertThat(sut.countObstacles(3, 1)).isEqualTo(7);
    assertThat(sut.countObstacles(5, 1)).isEqualTo(3);
    assertThat(sut.countObstacles(7, 1)).isEqualTo(4);
    assertThat(sut.countObstacles(1, 2)).isEqualTo(2);
  }

  @Test
  void given_theDay3Input_when_theExpectedResultIsProvided_then_theAnswerIsGiven()
      throws URISyntaxException, IOException {
    // Load input from file.
    Stream<String> input = getResourceAsStream("input-day_3.txt");

    ForestLayout map = new ForestLayout(input);
    RouteFinder sut = new RouteFinder(map);
    long count = sut.countObstacles(1, 1)
        * sut.countObstacles(3, 1)
        * sut.countObstacles(5, 1)
        * sut.countObstacles(7, 1)
        * sut.countObstacles(1, 2);

    assertThat(count).isNotNegative();

    logger.info("The result is " + count);
  }
}
