package com.clooker.solution.day.seven;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
public interface Bag {

  static BagVO.Builder builder() {
    return BagVO.builder();
  }

  String colorModifier();

  String color();
}
