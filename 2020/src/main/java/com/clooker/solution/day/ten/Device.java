package com.clooker.solution.day.ten;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
interface Device {

  JoltageAdapter joltageAdapter();

  static DeviceVO.Builder builder() {
    return DeviceVO.builder();
  }

  static Device from(List<JoltageAdapter> joltageAdapters) {
    final var joltage =
        joltageAdapters.parallelStream()
            .map(ja -> ja.rating().jolts())
            .max(Long::compare)
            .map(jolts -> Joltage.builder().jolts(jolts + 3).build())
            .orElse(null);

    final var joltageAdapter = JoltageAdapter.builder().rating(joltage).build();

    return builder().joltageAdapter(joltageAdapter).build();
  }
}
