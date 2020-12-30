package com.clooker.solution.day.ten;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class JoltageAdapters {

  public static JoltageAdapterVO.Builder builder() {
    return JoltageAdapterVO.builder();
  }

  public static List<JoltageAdapter> from(List<Long> joltsList) {
    return joltsList.stream()
        .sorted()
        .map(jolts -> Joltage.builder().jolts(jolts).build())
        .map(joltage -> builder().rating(joltage).build())
        .collect(Collectors.toList());
  }

  public static List<JoltageAdapter> of(List<JoltageAdapter> joltageAdapters, Device device) {
    final var result = new ArrayList<>(joltageAdapters);
    result.add(device.joltageAdapter());
    return result;
  }

  private JoltageAdapters() {}
}
