package com.clooker.solution.day.four;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

final class Solutions {

  private static final Path inputPath = Paths.get("src", "main", "resources", "4.input.txt");

  private static final List<String> passportFileLines =
      Utils.parseInput(inputPath).collect(Collectors.toList());

  private static final Solution<Integer> partOneSolution =
      Solution.<Integer>builder()
          .dayNumber(4)
          .part(Solution.Part.ONE)
          .solutionSupplier(() -> PassportFileParser.passports(passportFileLines).size())
          .build();

  private static final Solution<Long> partTwoSolution =
      Solution.<Long>builder()
          .dayNumber(4)
          .part(Solution.Part.TWO)
          .solutionSupplier(
              () -> {
                final var passportValidator = new PassportValidator();
                return PassportFileParser.passports(passportFileLines).parallelStream()
                    .filter(passportValidator::isValid)
                    .count();
              })
          .build();

  public static void main(String[] args) {
    List.of(partOneSolution, partTwoSolution).forEach(Solution::logSolution);
  }
}
