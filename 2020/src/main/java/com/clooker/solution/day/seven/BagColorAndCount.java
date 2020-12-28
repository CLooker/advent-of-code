package com.clooker.solution.day.seven;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
public interface BagColorAndCount {

  static BagColorAndCountVO.Builder builder() {
    return BagColorAndCountVO.builder();
  }

  BagColor bagColor();

  Integer count();
}
