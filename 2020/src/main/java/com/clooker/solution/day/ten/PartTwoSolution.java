package com.clooker.solution.day.ten;

import com.clooker.solution.common.Solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

final class PartTwoSolution {

  public static Solution<Long> solution(List<Long> input) {
    return Solution.<Long>builder()
        .dayNumber(10)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () -> {
              final long maxJolts = input.stream().max(Long::compare).map(x -> x + 3).orElseThrow();

              final Map<Long, Long> joltsToPathCount = new HashMap<>(Map.of(0L, 1L));

              Stream.concat(input.stream(), Stream.of(maxJolts))
                  .sorted()
                  .forEach(
                      jolts -> {
                        final long pathCount =
                            joltsToPathCount.getOrDefault(jolts - 3, 0L)
                                + joltsToPathCount.getOrDefault(jolts - 2, 0L)
                                + joltsToPathCount.getOrDefault(jolts - 1, 0L);

                        joltsToPathCount.put(jolts, pathCount);
                      });

              return joltsToPathCount.get(maxJolts);
            })
        .build();
  }

  private PartTwoSolution() {}
}
