package com.clooker.solution.day.seven;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Solutions {

  public static final List<String> inputFileLines =
      Utils.parseInput(Paths.get("src", "main", "resources", "7.input.txt"))
          .collect(Collectors.toList());

  public static void main(String[] args) {
    List.of(PartOneSolution.solution(inputFileLines), PartTwoSolution.solution((inputFileLines)))
        .forEach(Solution::logSolution);
  }
}
