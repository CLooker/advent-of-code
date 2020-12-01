package com.clooker.solution.day.one;

import common.Pair;
import common.Pairs;
import common.Solution;

import java.util.List;

public final class PartOneSolution {

  public static Solution<Long> solution(List<Long> expenses) {
    return Solution.<Long>builder()
        .dayNumber(1)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              final Pair<Long> pairThatSumsTo2020 =
                  Pairs.findFirst(expenses, (l1, l2) -> l1 + l2 == 2020L).orElse(null);

              return pairThatSumsTo2020 == null
                  ? null
                  : pairThatSumsTo2020.first() * pairThatSumsTo2020.second();
            })
        .build();
  }

  private PartOneSolution() {}
}
