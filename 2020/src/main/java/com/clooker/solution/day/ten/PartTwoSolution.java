package com.clooker.solution.day.ten;

import com.clooker.solution.common.Solution;

final class PartTwoSolution {

  public static Solution<Long> solution() {
    return Solution.<Long>builder()
        .dayNumber(10)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () -> {
              return 1L;
            })
        .build();
  }

  private PartTwoSolution() {}
}
