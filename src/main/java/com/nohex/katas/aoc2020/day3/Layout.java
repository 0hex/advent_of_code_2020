package com.nohex.katas.aoc2020.day3;

import java.util.Optional;

/**
 * A representation of a physical space with cartesian coordinates.
 *
 * @param <T> An enum representing the different features in the map.
 */
public interface Layout<T> {

  /**
   * Obtains the contents of the specified position.
   *
   * @param x The longitude.
   * @param y The latitude.
   * @return The feature at the specified location, {@link Optional#empty()} if none.
   */
  Optional<T> getAt(int x, int y);
}
