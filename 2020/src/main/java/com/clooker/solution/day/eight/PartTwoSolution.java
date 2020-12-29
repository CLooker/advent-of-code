package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;

import java.util.List;

final class PartTwoSolution {

  public static Solution<Integer> solution(List<String> inputFileLines) {
    return Solution.<Integer>builder()
        .dayNumber(8)
        .part(Solution.Part.TWO)
        .solutionSupplier(() -> LynxProgrammer.fixBug(Instructions.from(inputFileLines)))
        .build();
  }

  private PartTwoSolution() {}
}
