package com.clooker.solution.day.two;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

final class Solutions {

  private static final Path inputPath = Paths.get("src", "main", "resources", "2.input.txt");

  public static final List<String> passwordFileLines =
      Utils.parseInput(inputPath).collect(Collectors.toList());

  public static void main(String[] args) {
    List.of(PartOneSolution.solution(), PartTwoSolution.solution()).forEach(Solution::logSolution);
  }
}
