package com.clooker.solution.day.five;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Solutions {

  private static final Supplier<Stream<String>> boardingPassLinesSupplier =
      () -> Utils.parseInput(Paths.get("src", "main", "resources", "5.input.txt"));

  private static final Solution<Integer> partOneSolution =
      Solution.<Integer>builder()
          .dayNumber(5)
          .part(Solution.Part.ONE)
          .solutionSupplier(
              () ->
                  boardingPassLinesSupplier
                      .get()
                      .parallel()
                      .map(boardingPassLine -> new BoardingPass(boardingPassLine).seatId)
                      .max(Integer::compareTo)
                      .orElseThrow())
          .build();

  private static final Solution<Integer> partTwoSolution =
      Solution.<Integer>builder()
          .dayNumber(5)
          .part(Solution.Part.TWO)
          .solutionSupplier(
              () -> {
                final Set<Integer> seatIds =
                    boardingPassLinesSupplier
                        .get()
                        .parallel()
                        .map(boardingPassLine -> new BoardingPass(boardingPassLine).seatId)
                        .collect(Collectors.toSet());

                final var minSeatId = seatIds.stream().min(Integer::compareTo).orElseThrow();
                final var maxSeatId = seatIds.stream().max(Integer::compareTo).orElseThrow();

                return seatIds.parallelStream()
                        .filter(
                            seatId ->
                                (!seatId.equals(minSeatId) && !seatId.equals(maxSeatId))
                                        && !seatIds.contains(seatId - 1)
                                    || !seatIds.contains(seatId + 1))
                        .sorted()
                        .limit(1)
                        .findAny()
                        .orElseThrow()
                    + 1;
              })
          .build();

  public static void main(String[] args) {
    List.of(partOneSolution, partTwoSolution).forEach(Solution::logSolution);
  }
}
