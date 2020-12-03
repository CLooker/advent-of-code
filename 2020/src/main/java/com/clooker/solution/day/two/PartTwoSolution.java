package com.clooker.solution.day.two;

import com.clooker.solution.common.Solution;

import java.util.stream.Stream;

final class PartTwoSolution {

  public static Solution<Long> solution() {
    return Solution.<Long>builder()
        .dayNumber(2)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () ->
                Solutions.passwordFileLines.parallelStream()
                    .filter(
                        line ->
                            PasswordFileLineValidator.builder()
                                .passwordFileLine(
                                    PasswordFileLine.builder().passwordFileLine(line).build())
                                .validator(passwordFileLineValidator())
                                .build()
                                .isValid())
                    .count())
        .build();
  }

  private static Validator<PasswordFileLine> passwordFileLineValidator() {
    return passwordFileLine -> {
      final var policy = passwordFileLine.policy();
      final var indexOfDash = policy.indexOf("-");
      final var firstIndexToCheck = Integer.parseInt(policy.substring(0, indexOfDash)) - 1;
      final var secondIndexToCheck = Integer.parseInt(policy.substring(indexOfDash + 1)) - 1;
      final var password = passwordFileLine.password();
      final var charToMatch = passwordFileLine.charToMatch();

      return Stream.of(firstIndexToCheck, secondIndexToCheck)
              .filter(
                  indexToCheck ->
                      password.substring(indexToCheck, indexToCheck + 1).equals(charToMatch))
              .count()
          == 1;
    };
  }

  private PartTwoSolution() {}
}
