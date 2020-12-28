package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
