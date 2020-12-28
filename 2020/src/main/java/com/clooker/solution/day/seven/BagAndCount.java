package com.clooker.solution.day.seven;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
public interface BagAndCount {

  static BagAndCountVO.Builder builder() {
    return BagAndCountVO.builder();
  }

  Bag bag();

  int count();
}
