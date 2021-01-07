package com.nohex.katas.aoc2020.day4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PassportImplTest {

  @Test
  void given_theRequiredFields_when_aNewPassportIsRequested_then_aNewPassportIsCreated() {
    Map<String, String> fields = new HashMap<>();
    fields.put("byr", "");
    fields.put("iyr", "");
    fields.put("eyr", "");
    fields.put("hgt", "");
    fields.put("hcl", "");
    fields.put("ecl", "");
    fields.put("pid", "");

    assertThatNoException().isThrownBy(() -> new PassportImpl(fields));
  }

  @Test
  void given_requiredFieldsAreMissing_when_aNewPassportIsRequested_then_aNewPassportIsNotCreated() {
    Map<String, String> fields = new HashMap<>();
    fields.put("byr", "");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("iyr", "");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("eyr", "");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("hgt", "");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("hcl", "");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("ecl", "");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("pid", "");
    assertThatNoException().isThrownBy(() -> new PassportImpl(fields));
  }

  @Test
  void given_aNewPassport_when_cidIsNotSpecified_then_passportHasNoCid() {
    Map<String, String> fields = new HashMap<>();
    fields.put("byr", "");
    fields.put("iyr", "");
    fields.put("eyr", "");
    fields.put("hgt", "");
    fields.put("hcl", "");
    fields.put("ecl", "");
    fields.put("pid", "");

    Passport sut = new PassportImpl(fields);
    assertThat(sut.getCountryId()).isEmpty();
  }

  @Test
  void given_aPassport_when_cidIsSpecified_then_passportHasCid() {
    Map<String, String> fields = new HashMap<>();
    fields.put("byr", "");
    fields.put("iyr", "");
    fields.put("eyr", "");
    fields.put("hgt", "");
    fields.put("hcl", "");
    fields.put("ecl", "");
    fields.put("pid", "");
    fields.put("cid", "");

    Passport sut = new PassportImpl(fields);
    assertThat(sut.getCountryId()).isPresent();
  }
}