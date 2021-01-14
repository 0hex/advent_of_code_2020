package com.nohex.katas.aoc2020.day5;

import com.nohex.katas.aoc2020.day5.BinarySpacePartitioner.BinarySpace;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class SeatImpl implements Seat {

  private static final Logger logger = Logger.getAnonymousLogger();
  private static final Pattern VALID_CODE_PATTERN = Pattern.compile("^[FB]{7}[RL]{3}$");
  private final String code;
  private Integer id;

  SeatImpl(String code) {
    validate(code);
    this.code = code;
  }

  @Override
  public int getId() {
    if (null == id) {
      id = calculateId(code);
    }

    return id;
  }

  private static void validate(String code) {
    if (!VALID_CODE_PATTERN.matcher(code).find()) {
      throw new IllegalArgumentException(code + " is not a valid code");
    }
  }

  private static int calculateId(String code) {
    List<BinarySpace> rowSteps = convertToSpaces(code.substring(0, 7), 'F');
    List<BinarySpace> columnSteps = convertToSpaces(code.substring(7), 'L');

    // Search for column.
    BinarySpacePartitioner bsp = new BinarySpacePartitioner();
    int row = bsp.intReduce(rowSteps);
    int column = bsp.intReduce(columnSteps);

    int id = (row * 8) + column;

    logger.info(() -> String.format("%s: row %d, column %d, seat ID %d", code, row, column, id));

    return id;
  }

  /**
   * Converts a coded step string into a set of steps for BSP.
   *
   * @param encodedSteps  The code.
   * @param lowerHalfCode The code corresponding to the lower half step.
   * @return The steps.
   */
  private static List<BinarySpace> convertToSpaces(String encodedSteps, char lowerHalfCode) {
    return encodedSteps.chars()
        .mapToObj(i -> i == lowerHalfCode ? BinarySpace.LOWER : BinarySpace.UPPER)
        .collect(Collectors.toList());
  }

}
