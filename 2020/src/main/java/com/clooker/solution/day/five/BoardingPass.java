package com.clooker.solution.day.five;

import java.util.Objects;

final class BoardingPass {

  public final int row;
  public final int col;
  public final int seatId;

  public BoardingPass(String boardingPassLine) {
    Objects.requireNonNull(boardingPassLine);

    int minRow = 0;
    int maxRow = 127;
    int minCol = 0;
    int maxCol = 7;
    int charsProcessed = 0;
    int row = -1;
    int col = -1;

    String currBoardingPassLine = boardingPassLine;
    while (!currBoardingPassLine.isEmpty()) {
      char nextChar = currBoardingPassLine.charAt(0);
      charsProcessed++;
      switch (nextChar) {
        case 'F':
          {
            maxRow = (int) Math.floor((Math.abs(minRow + maxRow)) / 2);
            if (charsProcessed == 7) row = maxRow;
            break;
          }
        case 'B':
          {
            minRow = (int) (Math.floor(Math.abs(minRow + maxRow)) / 2) + 1;
            if (charsProcessed == 7) row = minRow;
            break;
          }
        case 'L':
          {
            maxCol = (int) Math.floor(Math.abs(minCol + maxCol) / 2);
            if (charsProcessed == 10) col = maxCol;
            break;
          }
        case 'R':
          {
            minCol = (int) (Math.floor(Math.abs(minCol + maxCol) / 2)) + 1;
            if (charsProcessed == 10) col = minCol;
            break;
          }
        default:
          {
            throw new IllegalArgumentException(nextChar + " must be one of [F, B, L, R]");
          }
      }
      currBoardingPassLine = currBoardingPassLine.substring(1);
    }

    this.row = row;
    this.col = col;
    this.seatId = (row * 8) + col;
  }
}
