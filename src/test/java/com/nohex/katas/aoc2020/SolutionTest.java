package com.nohex.katas.aoc2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Base class for tests used to find solutions to AoC puzzles.
 */
abstract public class SolutionTest {

  /**
   * Obtains a resource from the class path and returns it as a stream of strings.
   *
   * @param resourcePath The location of the resource in the class path.
   * @return The contents as a stream of strings.
   * @throws URISyntaxException An error converting the resource path to a URI.
   * @throws IOException        An error trying to read the resource.
   */
  static Stream<String> getResourceAsStream(String resourcePath)
      throws URISyntaxException, IOException {
    Path path = Paths.get(ClassLoader.getSystemResource(resourcePath).toURI());

    return Files.lines(path);
  }

}
