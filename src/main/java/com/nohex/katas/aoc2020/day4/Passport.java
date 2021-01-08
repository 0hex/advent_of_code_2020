package com.nohex.katas.aoc2020.day4;

import java.util.Optional;

/**
 * A passport, according to the AoC 2020 day 4 definition.
 */
interface Passport {

  int getBirthYear();

  int getIssueYear();

  int getExpirationYear();

  String getHeight();

  String getHairColor();

  String getEyeColor();

  String getPassportId();

  Optional<String> getCountryId();
}
