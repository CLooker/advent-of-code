package com.clooker.solution.day.ten;

import java.util.List;
import java.util.stream.Collectors;

final class Joltages {

  public static List<Joltage> of(List<Long> joltsList) {
    return joltsList.stream()
        .map(jolts -> Joltage.builder().jolts(jolts).build())
        .collect(Collectors.toList());
  }

  private Joltages() {}
}
