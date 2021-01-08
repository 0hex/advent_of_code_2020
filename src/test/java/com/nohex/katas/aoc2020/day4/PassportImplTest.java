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
    fields.put("byr", "1970");
    fields.put("iyr", "2010");
    fields.put("eyr", "2020");
    fields.put("hgt", "180cm");
    fields.put("hcl", "#000000");
    fields.put("ecl", "hzl");
    fields.put("pid", "012345678");

    assertThatNoException().isThrownBy(() -> new PassportImpl(fields));
  }

  @Test
  void given_requiredFieldsAreMissing_when_aNewPassportIsRequested_then_aNewPassportIsNotCreated() {
    Map<String, String> fields = new HashMap<>();
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("byr", "1970");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("iyr", "2010");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("eyr", "2020");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("hgt", "180cm");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("hcl", "#000000");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("ecl", "hzl");
    assertThatIllegalStateException().isThrownBy(() -> new PassportImpl(fields));

    fields.put("pid", "123456789");
    assertThatNoException().isThrownBy(() -> new PassportImpl(fields));
  }

  @Test
  void given_aNewPassport_when_cidIsNotSpecified_then_passportHasNoCid() {
    Map<String, String> fields = new HashMap<>();
    fields.put("byr", "1970");
    fields.put("iyr", "2010");
    fields.put("eyr", "2020");
    fields.put("hgt", "180cm");
    fields.put("hcl", "#000000");
    fields.put("ecl", "hzl");
    fields.put("pid", "012345678");

    Passport sut = new PassportImpl(fields);
    assertThat(sut.getCountryId()).isEmpty();
  }

  @Test
  void given_aPassport_when_cidIsSpecified_then_passportHasCid() {
    Map<String, String> fields = new HashMap<>();
    fields.put("byr", "1970");
    fields.put("iyr", "2010");
    fields.put("eyr", "2020");
    fields.put("hgt", "180cm");
    fields.put("hcl", "#000000");
    fields.put("ecl", "hzl");
    fields.put("pid", "012345678");
    fields.put("cid", "123");

    Passport sut = new PassportImpl(fields);
    assertThat(sut.getCountryId()).isPresent();
  }
}