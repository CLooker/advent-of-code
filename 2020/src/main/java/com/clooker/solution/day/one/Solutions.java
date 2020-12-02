package com.clooker.solution.day.one;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Solutions {

  private static final Path inputPath = Paths.get("src", "main", "resources", "1.input.txt");

  private static final List<Long> expenses =
      Utils.parseInput(inputPath).parallelStream().map(Long::valueOf).collect(Collectors.toList());

  public static void main(String[] args) {
    List.of(PartOneSolution.solution(expenses), PartTwoSolution.solution(expenses))
        .forEach(Solution::logSolution);
  }

  private Solutions() {}
}
