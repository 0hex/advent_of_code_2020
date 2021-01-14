package com.nohex.katas.aoc2020.day5;

import static org.assertj.core.api.Assertions.assertThat;

import com.nohex.katas.aoc2020.day5.BinarySpacePartitioner.BinarySpace;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class BinarySpacePartitionerTest {

  @Test
  void given_oneStep_when_intReduce_then_expectedResults() {
    BinarySpacePartitioner sut = new BinarySpacePartitioner();
    assertThat(sut.intReduce(listOf(1, BinarySpace.LOWER))).isZero();
    assertThat(sut.intReduce(listOf(1, BinarySpace.UPPER))).isOne();
  }

  @Test
  void given_listOfLowerSteps_when_intReduce_then_resultIsZero() {
    BinarySpacePartitioner sut = new BinarySpacePartitioner();

    IntStream.range(1, 10).forEach(
        i -> assertThat(sut.intReduce(listOf(i, BinarySpace.LOWER))).isZero()
    );
  }

  @Test
  void given_listOfUpperSteps_when_intReduce_then_resultIs2PowerNumberOfSteps() {
    BinarySpacePartitioner sut = new BinarySpacePartitioner();

    IntStream.range(1, 10).forEach(
        i -> assertThat(sut.intReduce(listOf(i, BinarySpace.UPPER))).isEqualTo((2 << (i - 1)) - 1)
    );
  }

  private List<BinarySpace> listOf(int i, BinarySpace half) {
    List<BinarySpace> result;
    result = (i == 1)
        ? Collections.singletonList(half)
        : IntStream.rangeClosed(1, i).mapToObj(n -> half).collect(Collectors.toList());

    return result;
  }
}