package com.nohex.katas.aoc2020.day4;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A factory for {@link Passport} objects.
 */
class PassportFactory {

  private static final Logger logger = Logger.getAnonymousLogger();

  private PassportFactory() {
    // This factory needs not being instanced.
  }

  /**
   * Parses textual data into passports.
   *
   * @param input The passport data.
   * @return The valid passports that could be extracted from the input.txt.
   */
  static List<Passport> parse(Reader input) {
    List<Passport> passports = new ArrayList<>();
    Map<String, String> fields = new HashMap<>();

    try (Scanner scanner = new Scanner(input)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();

        // If there is a blank line, try to create a passport with the current fields.
        if (line.isBlank()) {
          PassportFactory.fromFields(fields).ifPresent(passports::add);
          // Prepare for the next passport.
          fields.clear();
        } else {
          // Split ket/value tuples.
          String[] tuples = line.split("\\s+");
          for (String tuple : tuples) {
            // Split key and value.
            String[] parts = tuple.split(":");
            if (parts.length == 2) {
              fields.put(parts[0], parts[1]);
            }
          }
        }
      }

      // If there are fields left, try to create one last passport.
      if (!fields.isEmpty()) {
        PassportFactory.fromFields(fields).ifPresent(passports::add);
      }
    }

    return passports;
  }

  /**
   * Creates a passport from textual fields.
   *
   * @param fields The textual fields.
   * @return A passport, if a valid one could be created from the fields.
   */
  static Optional<Passport> fromFields(Map<String, String> fields) {
    try {
      return Optional.of(new PassportImpl(fields));
    } catch (IllegalStateException | IllegalArgumentException e) {
      logger.info(e::getMessage);
      return Optional.empty();
    }
  }
}
