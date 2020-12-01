package com.clooker.solution.day.one;

import common.Solution;
import common.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PartTwoSolution {

  public static Solution<Long> solution(List<Long> expenses) {
    return Solution.<Long>builder()
        .dayNumber(1)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () -> {
              final Map<Long, Long> expenseToFrequency = Utils.elementToFrequency(expenses);

              final List<Long> matches =
                  expenses.parallelStream()
                      .map(
                          expense -> {
                            var match =
                                expenses.parallelStream()
                                    .filter(
                                        e2 -> {
                                          var missingExpense = 2020 - expense - e2;
                                          return expenseToFrequency.containsKey(missingExpense);
                                        })
                                    .findFirst()
                                    .orElse(null);

                            return match == null
                                ? null
                                : List.of(expense, match, 2020 - expense - match);
                          })
                      .filter(Objects::nonNull)
                      .findFirst()
                      .orElse(List.of());

              return matches.parallelStream().reduce((l1, l2) -> l1 * l2).orElse(null);
            })
        .build();
  }

  private PartTwoSolution() {}
}
