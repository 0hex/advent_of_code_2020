package com.nohex.katas.aoc2020.day2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class PasswordPolicyTest {

  @Test
  void given_aPolicy_when_aCompliantPasswordIsChecked_then_isValidReturnsTrue() {
    PasswordPolicy sut = new PasswordPolicy("1-3 a");
    assertThat(sut.isValid("aac")).isTrue();
    assertThat(sut.isValid("caa")).isTrue();
  }

  @Test
  void given_aPolicy_when_aNonCompliantPasswordIsChecked_then_isValidReturnsFalse() {
    PasswordPolicy sut = new PasswordPolicy("1-3 a");
    assertThat(sut.isValid("aca")).isFalse();
  }

  @Test
  void given_anEncodedPolicy_when_aPolicyIsInstanced_then_thePolicyIsCreated() {
    // Stream of correctly encoded policies.
    Stream.of(
        "1-3 a",
        "11-33 b",
        "1-999 c"
    ).forEach(
        encodedPolicy ->
            assertThatNoException()
                .isThrownBy(() -> new PasswordPolicy(encodedPolicy))
    );
  }

  @Test
  void given_aMalformedEncodedPolicy_when_aPolicyIsInstance_then_theInstatiationFails() {
    // Stream of malformed policies.
    Stream.of(
        "1-3 1",
        "-3 a",
        "a-3 1",
        "1-2a",
        "3-1 a"
    ).forEach(
        encodedPolicy ->
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new PasswordPolicy(encodedPolicy))
    );
  }
}