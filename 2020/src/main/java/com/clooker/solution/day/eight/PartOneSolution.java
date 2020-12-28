package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
