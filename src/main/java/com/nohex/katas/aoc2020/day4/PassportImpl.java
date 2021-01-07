package com.nohex.katas.aoc2020.day4;

import java.util.Map;
import java.util.Optional;

public class PassportImpl implements Passport {

  private final String birthYear;
  private final String issueYear;
  private final String expirationYear;
  private final String height;
  private final String hairColor;
  private final String eyeColor;
  private final String passportId;
  private final Optional<String> countryId;

  public PassportImpl(Map<String, String> fields) {
    birthYear = validate("Birth year", fields.get("byr"));
    eyeColor = validate("Eye color", fields.get("ecl"));
    expirationYear = validate("Expiration year", fields.get("eyr"));
    hairColor = validate("Hair color", fields.get("hcl"));
    height = validate("Height", fields.get("hgt"));
    issueYear = validate("Issue year", fields.get("iyr"));
    passportId = validate("Passport ID", fields.get("pid"));

    String cid = fields.get("cid");
    countryId = cid == null ? Optional.empty() : Optional.of(cid);
  }

  @Override
  public String getBirthYear() {
    return birthYear;
  }

  @Override
  public String getIssueYear() {
    return issueYear;
  }

  @Override
  public String getExpirationYear() {
    return expirationYear;
  }

  @Override
  public String getHeight() {
    return height;
  }

  @Override
  public String getHairColor() {
    return hairColor;
  }

  @Override
  public String getEyeColor() {
    return eyeColor;
  }

  @Override
  public String getPassportId() {
    return passportId;
  }

  @Override
  public Optional<String> getCountryId() {
    return countryId;
  }

  private static String validate(String fieldName, String contents) {
    if (contents == null) {
      throw new IllegalStateException("Required field is missing: " + fieldName);
    }

    return contents;
  }
}
