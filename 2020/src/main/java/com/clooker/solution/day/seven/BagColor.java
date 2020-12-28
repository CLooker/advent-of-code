package com.clooker.solution.day.seven;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
public interface BagColor {

  static BagColorVO.Builder builder() {
    return BagColorVO.builder();
  }

  String colorModifier();

  String color();
}
