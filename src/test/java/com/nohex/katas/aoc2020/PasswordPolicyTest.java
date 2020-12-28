package com.nohex.katas.aoc2020;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class PasswordPolicyTest {

  @Test
  void given_aPolicy_when_aCompliantPasswordIsChecked_then_isValidReturnsTrue() {
    PasswordPolicy sut = new PasswordPolicy("1-3 a");
    assertThat(sut.isValid("a")).isTrue();
    assertThat(sut.isValid("aa")).isTrue();
    assertThat(sut.isValid("aaa")).isTrue();
    assertThat(sut.isValid("ababa")).isTrue();
    assertThat(sut.isValid(" aaa ")).isTrue();
  }

  @Test
  void given_aPolicy_when_aNonCompliantPasswordIsChecked_then_isValidReturnsFalse() {
    PasswordPolicy sut = new PasswordPolicy("1-3 a");
    assertThat(sut.isValid("")).isFalse();
    assertThat(sut.isValid("aaaa")).isFalse();
  }

  @Test
  void given_anEncodedPolicy_when_aPolicyIsInstanced_then_thePolicyIsCreated() {
    // Stream of correctly encoded policies.
    Stream.of(
        "1-3 a",
        "11-33 b",
        "0-123456789 c"
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