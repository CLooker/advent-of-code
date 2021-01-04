package com.clooker.solution.day.ten;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

final class Solutions {

  static final List<Long> input =
      Utils.parseInput(Paths.get("src", "main", "resources", "10.input.txt"))
          .map(Long::parseLong)
          .collect(Collectors.toList());

  public static void main(String[] args) {
    List.of(PartOneSolution.solution(input), PartTwoSolution.solution(input))
        .forEach(Solution::logSolution);
  }
}
