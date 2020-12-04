package com.clooker.solution.day.two;

import com.clooker.solution.common.Solution;

import java.util.regex.Pattern;

final class PartOneSolution {

  public static Solution<Long> solution() {
    return com.clooker.solution.common.Solution.<Long>builder()
        .dayNumber(2)
        .part(Solution.Part.ONE)
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
      final var pattern = Pattern.compile(passwordFileLine.charToMatch());
      final var matcher = pattern.matcher(passwordFileLine.password());
      final var matches = matcher.results().count();

      final var policy = passwordFileLine.policy();
      final var indexOfDash = policy.indexOf("-");
      final var minOccurrencesOfCharToMatch = Integer.valueOf(policy.substring(0, indexOfDash));
      final var maxOccurrencesOfCharToMatch = Integer.valueOf(policy.substring(indexOfDash + 1));

      return matches >= minOccurrencesOfCharToMatch && matches <= maxOccurrencesOfCharToMatch;
    };
  }

  private PartOneSolution() {}
}
