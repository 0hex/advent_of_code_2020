package com.nohex.katas.aoc2020.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class ForestLayoutTest {

  @Test
  void testIsObstacle() {
    Stream<String> encodedMap = Stream.of(
        "...#",
        ".#.#",
        "#..#"
    );
    ForestLayout sut = new ForestLayout(encodedMap);

    assertThat(sut.getAt(3, 0)).isPresent();
    assertThat(sut.getAt(1, 1)).isPresent();
    assertThat(sut.getAt(3, 1)).isPresent();
    assertThat(sut.getAt(0, 2)).isPresent();
  }

  @Test
  void testWrapAround() {
    Stream<String> encodedMap = Stream.of("...#");
    ForestLayout sut = new ForestLayout(encodedMap);
    sut.draw();

    assertThat(sut.getAt(0, 0)).isEmpty();
    assertThat(sut.getAt(1, 0)).isEmpty();
    assertThat(sut.getAt(2, 0)).isEmpty();
    assertThat(sut.getAt(3, 0)).isPresent();

    assertThat(sut.getAt(4, 0)).isEmpty();
    assertThat(sut.getAt(5, 0)).isEmpty();
    assertThat(sut.getAt(6, 0)).isEmpty();
    assertThat(sut.getAt(7, 0)).isPresent();

    assertThat(sut.getAt(7, 0)).isPresent();
  }
}