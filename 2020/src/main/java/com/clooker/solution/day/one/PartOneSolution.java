package com.clooker.solution.day.one;

import com.clooker.solution.common.Pairs;
import com.clooker.solution.common.Solution;

import java.util.List;

public final class PartOneSolution {

  public static Solution<Long> solution(List<Long> expenses) {
    return Solution.<Long>builder()
        .dayNumber(1)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () ->
                Pairs.findFirst(expenses, (l1, l2) -> l1 + l2 == 2020L)
                    .map(pair -> pair.first() * pair.second())
                    .orElse(null))
        .build();
  }

  private PartOneSolution() {}
}
