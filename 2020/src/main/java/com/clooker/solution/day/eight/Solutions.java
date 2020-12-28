package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

final class Solutions {

  public static final List<String> inputFileLines =
      Utils.parseInput(Paths.get("src", "main", "resources", "8.input.txt"))
          .collect(Collectors.toList());

  public static void main(String[] args) {
    List.of(PartOneSolution.solution(inputFileLines), PartTwoSolution.solution(inputFileLines))
        .forEach(Solution::logSolution);
  }
}
