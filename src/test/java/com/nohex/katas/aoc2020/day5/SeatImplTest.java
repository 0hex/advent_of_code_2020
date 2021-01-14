package com.nohex.katas.aoc2020.day5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class SeatImplTest {

  @Test
  void given_code_when_getId_then_idIsCalculated() {
    String code = "FBFBBFFRLR";

    assertThat(new SeatImpl(code).getId()).isEqualTo(357);
  }

  @Test
  void given_tooManyRows_when_new_then_noInstance() {
    assertThatIllegalArgumentException().isThrownBy(() -> new SeatImpl("FFFFFFFFRRR"));
  }

  @Test
  void given_tooFewRows_when_new_then_noInstance() {
    assertThatIllegalArgumentException().isThrownBy(() -> new SeatImpl("FFFFFFRRR"));
  }

  @Test
  void given_tooManyColumns_when_new_then_noInstance() {
    assertThatIllegalArgumentException().isThrownBy(() -> new SeatImpl("FFFFFFFRR"));
  }

  @Test
  void given_tooFewColumns_when_new_then_noInstance() {
    assertThatIllegalArgumentException().isThrownBy(() -> new SeatImpl("FFFFFFFRRRR"));
  }

  @Test
  void given_invalidCharacter_when_new_then_noInstance() {
    assertThatIllegalArgumentException().isThrownBy(() -> new SeatImpl("ABCDEFGRRR"));
  }
}