package com.nohex.katas.aoc2020.day3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * A map of a forest, as depicted in the day 3 puzzle of AoC 2020.
 */
class ForestLayout implements Layout<ForestFeature> {

  private static final Logger logger = Logger.getAnonymousLogger();

  private static final char BLANK_SYMBOL = ForestFeature.BLANK.getSymbol();
  private final Map<Position, ForestFeature> features;
  private final int mapHeight;
  private final int mapWidth;

  ForestLayout(Stream<String> encodedMap) {
    features = new HashMap<>();

    // Store the map as a sparse matrix for storage efficiency.
    final AtomicInteger width = new AtomicInteger();
    final AtomicInteger height = new AtomicInteger();
    encodedMap.forEach(line -> {
      final int lineLength = line.length();
      if (width.get() == 0) {
        width.set(lineLength);
      }
      // Examine each character while keeping track of its position.
      for (int x = 0; x < lineLength; x++) {
        char current = line.charAt(x);
        // Only non-blank cells are stored.
        if (current != BLANK_SYMBOL) {
          features.put(
              new Position(x, height.get()),
              ForestFeature.fromSymbol(current)
                  .orElseThrow(() -> new IllegalArgumentException(
                      "Symbol not recognised"
                  ))
          );
        }
      }
      // Account for the row that has just been parsed.
      height.getAndIncrement();
    });

    // Dimensions of the map, recorded to help with the cell wrap-around.
    mapWidth = width.get();
    mapHeight = height.get();
  }

  /**
   * Gets the contents of a map cell.
   *
   * @param x The longitude.
   * @param y The latitude.
   * @return The contents of the cell.
   */
  @Override
  public Optional<ForestFeature> getAt(int x, int y) {
    if (y > mapHeight) {
      throw new IllegalArgumentException("Latitude is outside the map");
    }

    ForestFeature contents = features.get(new Position(x % mapWidth, y));

    return contents == null
        ? Optional.empty()
        : Optional.of(contents);
  }

  /**
   * Logs a representation of the map.
   */
  void draw() {
    StringBuilder sb = new StringBuilder();
    sb.append('\n');
    for (int y = 0; y < mapHeight; y++) {
      for (int x = 0; x < mapWidth; x++) {
        sb.append(getAt(x, y).orElse(ForestFeature.BLANK).getSymbol());
      }
      sb.append('\n');
    }

    logger.info(sb::toString);
  }


  public int getHeight() {
    return mapHeight;
  }

  public int getWidth() {
    return mapWidth;
  }

  private static class Position {

    private int x;
    private int y;

    private Position(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Position)) {
        return false;
      }
      Position position = (Position) o;
      return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
