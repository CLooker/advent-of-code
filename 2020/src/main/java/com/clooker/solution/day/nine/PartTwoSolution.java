package com.clooker.solution.day.nine;

import com.clooker.solution.common.Solution;
import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class PartTwoSolution {

  public static Solution<Long> solution(List<Long> input, int preambleLength) {
    return Solution.<Long>builder()
        .dayNumber(9)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () -> {
              final List<Integer> indices =
                  IntStream.range(0, input.size()).boxed().collect(Collectors.toList());

              final long targetSum = PartOneSolution.solution(input, preambleLength).solution();

              return Generator.cartesianProduct(indices, indices).stream()
                  .filter(int_int -> int_int.get(0) < int_int.get(1))
                  .map(low_high -> input.subList(low_high.get(0), low_high.get(1)))
                  .filter(sublist -> sublist.stream().reduce(0L, Long::sum) == targetSum)
                  .findAny()
                  .map(
                      sublistThatSumsToTargetSum -> {
                        final List<Long> sorted =
                            sublistThatSumsToTargetSum.stream()
                                .sorted()
                                .collect(Collectors.toList());

                        return sorted.get(0) + sorted.get(sorted.size() - 1);
                      })
                  .orElseThrow(() -> new IllegalStateException("should not get here"));
            })
        .build();
  }

  private PartTwoSolution() {}
}
