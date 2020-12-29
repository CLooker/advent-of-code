package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;

import java.util.List;

final class PartOneSolution {

  public static Solution<Integer> solution(List<String> inputFileLines) {
    return Solution.<Integer>builder()
        .dayNumber(8)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              try {
                return Lynx.run(Instructions.from(inputFileLines));
              } catch (LynxException e) {
                return e.accumulator();
              }
            })
        .build();
  }

  private PartOneSolution() {}
}
