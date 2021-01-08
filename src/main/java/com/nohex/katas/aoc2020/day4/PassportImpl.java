package com.nohex.katas.aoc2020.day4;

import static java.lang.String.format;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportImpl implements Passport {

  private static final Set<String> REQUIRED_FIELDS = Stream
      .of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").collect(Collectors.toSet());
  private static final Pattern HAIR_COLOR_PATTERN = Pattern.compile("#[0-9a-f]{6}");
  private static final Set<String> EYE_COLOURS = Stream
      .of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").collect(Collectors.toSet());
  private static final Pattern PASSPORT_ID_PATTERN = Pattern.compile("\\d{9}");
  private final int birthYear;
  private final int issueYear;
  private final int expirationYear;
  private final String height;
  private final String hairColor;
  private final String eyeColor;
  private final String passportId;
  private final String countryId;

  public PassportImpl(Map<String, String> fields) {
    validateRequiredFields(fields.keySet());

    birthYear = validateYear("Birth year", fields.get("byr"), 1920, 2002);
    issueYear = validateYear("Issue year", fields.get("iyr"), 2010, 2020);
    expirationYear = validateYear("Expiration year", fields.get("eyr"), 2020, 2030);
    height = validateHeight(fields.get("hgt"));
    hairColor = validateHairColor(fields.get("hcl"));
    eyeColor = validateEyeColor(fields.get("ecl"));
    passportId = validatePassportId(fields.get("pid"));

    countryId = fields.get("cid");
  }

  private void validateRequiredFields(Set<String> fieldNames) {
    Set<String> missingFields = new HashSet<>(REQUIRED_FIELDS);
    missingFields.removeAll(fieldNames);
    if (!missingFields.isEmpty()) {
      throw new IllegalStateException(
          "Required fields are missing: " + String.join(", ", missingFields));
    }
  }

  private int validateYear(String name, String value, int minimum, int maximum) {
    int year;
    try {
      year = Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The year is not a number");
    }
    if (year < minimum) {
      throw new IllegalArgumentException(format("%s must be at least %d", name, minimum));
    }
    if (year > maximum) {
      throw new IllegalArgumentException(format("%s must be at most %d", name, maximum));
    }

    return year;
  }

  private String validateHeight(String value) {
    String units;
    int measure;

    try {
      int unitsPosition = value.indexOf("cm");
      if (unitsPosition == -1) {
        unitsPosition = value.indexOf("in");
      }
      if (unitsPosition < 1) {
        throw new IllegalArgumentException("Unrecognised height format");
      }
      units = value.substring(unitsPosition);
      measure = Integer.parseInt(value.substring(0, unitsPosition));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The height is not a number");
    }

    if ((units.equals("cm") && (measure < 150))
        || (units.equals("in") && (measure < 59))) {
      throw new IllegalArgumentException("Too short");
    }

    if ((units.equals("cm") && (measure > 193))
        || (units.equals("in") && (measure > 76))) {
      throw new IllegalArgumentException("Too tall");
    }

    return value;
  }

  private String validateHairColor(String value) {
    if (!HAIR_COLOR_PATTERN.matcher(value).find()) {
      throw new IllegalArgumentException("Unrecognised hair colour format");
    }

    return value;
  }

  private String validateEyeColor(String value) {
    if (!EYE_COLOURS.contains(value)) {
      throw new IllegalArgumentException("Unknown eye colour");
    }

    return value;
  }

  private String validatePassportId(String value) {
    if (!PASSPORT_ID_PATTERN.matcher(value).find()) {
      throw new IllegalArgumentException("Unrecognised passport ID format");
    }

    return value;
  }

  @Override
  public int getBirthYear() {
    return birthYear;
  }

  @Override
  public int getIssueYear() {
    return issueYear;
  }

  @Override
  public int getExpirationYear() {
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
    return countryId == null ? Optional.empty() : Optional.of(countryId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PassportImpl)) {
      return false;
    }
    PassportImpl passport = (PassportImpl) o;
    return birthYear == passport.birthYear && issueYear == passport.issueYear
        && expirationYear == passport.expirationYear && height.equals(passport.height) && hairColor
        .equals(passport.hairColor) && eyeColor.equals(passport.eyeColor) && passportId
        .equals(passport.passportId) && Objects.equals(countryId, passport.countryId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId,
            countryId);
  }
}
