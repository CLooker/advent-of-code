package com.clooker.solution.day.nine;

import com.clooker.solution.common.Solution;
import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;

final class PartOneSolution {

  public static Solution<Long> solution(List<String> inputFileLines, int preambleLength) {
    return Solution.<Long>builder()
        .dayNumber(8)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              List<Long> previousNums =
                  toLongList(inputFileLines).stream()
                      .limit(preambleLength)
                      .collect(Collectors.toList());

              for (Long targetSum :
                  toLongList(inputFileLines).stream()
                      .skip(preambleLength)
                      .collect(Collectors.toList())) {

                final boolean canMatchTargetSum =
                    Generator.combination(previousNums).simple(2).stream()
                        .parallel()
                        .anyMatch(pair -> pair.get(0) + pair.get(1) == targetSum);

                if (!canMatchTargetSum) return targetSum;

                previousNums.add(targetSum);
                previousNums = previousNums.stream().skip(1).collect(Collectors.toList());
              }

              throw new IllegalStateException("should not get here");
            })
        .build();
  }

  private static List<Long> toLongList(List<String> strings) {
    return strings.stream().map(Long::parseLong).collect(Collectors.toList());
  }

  private PartOneSolution() {}
}
