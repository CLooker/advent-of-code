package com.clooker.solution.day.ten;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
public interface Joltage {

  static JoltageVO.Builder builder() {
    return JoltageVO.builder();
  }

  long jolts();
}
