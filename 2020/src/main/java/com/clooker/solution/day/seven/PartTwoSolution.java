package com.clooker.solution.day.seven;

import com.clooker.solution.common.Solution;

import java.util.Collections;
import java.util.List;

final class PartTwoSolution {

  public static Solution<Long> solution(List<String> inputFileLines) {
    return Solution.<Long>builder()
        .dayNumber(7)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () -> {
              final BagRules bagRules = BagRules.from(inputFileLines);
              final Bag container = Bag.builder().colorModifier("shiny").color("gold").build();

              return containeeCount(bagRules, container) - 1;
            })
        .build();
  }

  static long containeeCount(BagRules bagRules, Bag container) {
    return bagRules
        .bagRule(container)
        .map(BagRule::containees)
        .orElse(Collections.emptyList())
        .stream()
        .map(bagAndCount -> bagAndCount.count() * containeeCount(bagRules, bagAndCount.bag()))
        .reduce(1L, Long::sum);
  }
}
