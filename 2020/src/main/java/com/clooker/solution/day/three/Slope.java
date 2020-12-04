package com.clooker.solution.day.three;

import org.immutables.value.Value;

@Value.Immutable
interface Slope {

  static ImmutableSlope.Builder builder() {
    return ImmutableSlope.builder();
  }

  int horizontal();

  int vertical();
}
