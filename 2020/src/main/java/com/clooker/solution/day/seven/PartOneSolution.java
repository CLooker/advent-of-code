package com.clooker.solution.day.seven;

import com.clooker.solution.common.Solution;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

final class PartOneSolution {

  public static Solution<Integer> solution(List<String> inputFileLines) {
    return Solution.<Integer>builder()
        .dayNumber(7)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              final BagColor bagColorToContain =
                  BagColor.builder().colorModifier("shiny").color("gold").build();

              final BagRules bagRules = BagRules.from(inputFileLines);

              return bagRules.containers().parallelStream()
                  .map(
                      container ->
                          bagRules.containees(container).contains(bagColorToContain)
                              ? container
                              : null)
                  .filter(Objects::nonNull)
                  .collect(Collectors.toSet())
                  .size();
            })
        .build();
  }
}
