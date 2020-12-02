package com.clooker.solution.day.one;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

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

              return expenses.parallelStream()
                  .map(
                      expense ->
                          expenses.parallelStream()
                              .map(
                                  secondExpense -> {
                                    var thirdExpense = 2020 - expense - secondExpense;
                                    return expenseToFrequency.containsKey(thirdExpense)
                                        ? List.of(expense, secondExpense, thirdExpense)
                                        : null;
                                  })
                              .filter(Objects::nonNull)
                              .findFirst()
                              .orElse(null))
                  .filter(Objects::nonNull)
                  .findFirst()
                  .flatMap(
                      expensesThatSumTo2020 ->
                          expensesThatSumTo2020.parallelStream().reduce((l1, l2) -> l1 * l2))
                  .orElse(null);
            })
        .build();
  }

  private PartTwoSolution() {}
}
