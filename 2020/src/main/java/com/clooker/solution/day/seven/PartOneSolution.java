package com.clooker.solution.day.seven;

import com.clooker.solution.common.Solution;

import java.util.List;
import java.util.stream.Collectors;

final class PartOneSolution {

  public static Solution<Integer> solution(List<String> inputFileLines) {
    return Solution.<Integer>builder()
        .dayNumber(7)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              final Bag bagToContain = Bag.builder().colorModifier("shiny").color("gold").build();
              final BagRules bagRules = BagRules.from(inputFileLines);
              
              return bagRules.containers().parallelStream()
                  .filter(container -> bagRules.walkContainees(container).contains(bagToContain))
                  .collect(Collectors.toSet())
                  .size();
            })
        .build();
  }
}
