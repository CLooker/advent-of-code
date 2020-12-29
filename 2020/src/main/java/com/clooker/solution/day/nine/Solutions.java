package com.clooker.solution.day.nine;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

final class Solutions {

  private static final List<Long> input =
      Utils.parseInput(Paths.get("src", "main", "resources", "9.input.txt"))
          .map(Long::parseLong)
          .collect(Collectors.toList());

  private static final int preambleLength = 25;

  public static void main(String[] args) {
    List.of(PartOneSolution.solution(input, 25), PartTwoSolution.solution(input, 25))
        .forEach(Solution::logSolution);
  }
}
