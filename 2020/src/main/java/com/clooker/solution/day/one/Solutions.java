package com.clooker.solution.day.one;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;
import org.paukov.combinatorics3.Generator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

final class Solutions {

  private static final Path inputPath = Paths.get("src", "main", "resources", "1.input.txt");

  private static final List<Long> expenses =
      Utils.parseInput(inputPath).parallel().map(Long::valueOf).collect(Collectors.toList());

  private static final Solution<Long> partOneSolution =
      Solution.<Long>builder()
          .dayNumber(1)
          .part(Solution.Part.ONE)
          .solutionSupplier(solutionSupplier(expenses, 2, 2020L))
          .build();

  private static final Solution<Long> partTwoSolution =
      Solution.<Long>builder()
          .dayNumber(1)
          .part(Solution.Part.TWO)
          .solutionSupplier(solutionSupplier(expenses, 3, 2020L))
          .build();

  public static void main(String[] args) {
    List.of(partOneSolution, partTwoSolution).forEach(Solution::logSolution);
  }

  private static Supplier<Long> solutionSupplier(
      List<Long> longs, int combinationSize, Long combinationSumTarget) {
    return () ->
        Generator.combination(longs).simple(combinationSize).stream()
            .parallel()
            .map(ls -> ls.stream().reduce(0L, Long::sum).equals(combinationSumTarget) ? ls : null)
            .filter(Objects::nonNull)
            .findFirst()
            .flatMap(ls -> ls.parallelStream().reduce((l1, l2) -> l1 * l2))
            .orElse(null);
  }

  private Solutions() {}
}
