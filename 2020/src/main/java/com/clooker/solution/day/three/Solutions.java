package com.clooker.solution.day.three;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

final class Solutions {

  private static final Path inputPath = Paths.get("src", "main", "resources", "3.input.txt");

  private static final List<String> landscapeLines = Utils.parseInput(inputPath);

  private static final Solution<Long> partOneSolution =
      Solution.<Long>builder()
          .dayNumber(3)
          .part(Solution.Part.ONE)
          .solutionSupplier(
              () ->
                  hitTreesCount(landscapeLines, Slope.builder().horizontal(3).vertical(-1).build()))
          .build();

  private static final Solution<Long> partTwoSolution =
      Solution.<Long>builder()
          .dayNumber(3)
          .part(Solution.Part.TWO)
          .solutionSupplier(
              () ->
                  List.of(
                          Slope.builder().horizontal(1).vertical(-1).build(),
                          Slope.builder().horizontal(3).vertical(-1).build(),
                          Slope.builder().horizontal(5).vertical(-1).build(),
                          Slope.builder().horizontal(7).vertical(-1).build(),
                          Slope.builder().horizontal(1).vertical(-2).build())
                      .parallelStream()
                      .map(slope -> hitTreesCount(Solutions.landscapeLines, slope))
                      .reduce(1L, (l1, l2) -> l1 * l2))
          .build();

  public static void main(String[] args) {
    List.of(partOneSolution, partTwoSolution).forEach(Solution::logSolution);
  }

  private static Long hitTreesCount(List<String> landscapeLines, Slope slope) {
    final var horizontal = slope.horizontal();
    final var vertical = slope.vertical();
    final long height = landscapeLines.size();
    final char open = '.';

    long hitTreesCount = 0L;
    int currX = 0;
    int currY = 0;

    while (currY < height) {
      final var landscapeLine = landscapeLines.get(currY);

      final var isOpen = landscapeLine.charAt(currX) == open;
      if (!isOpen) hitTreesCount++;

      currX += horizontal;
      if (currX >= landscapeLine.length()) {
        currX = (currX % landscapeLine.length());
      }

      currY -= vertical;
    }

    return hitTreesCount;
  }
}
