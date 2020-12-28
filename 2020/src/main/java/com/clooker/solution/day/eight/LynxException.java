package com.clooker.solution.day.eight;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
abstract class LynxException extends RuntimeException {

  public static LynxExceptionVO.Builder builder() {
    return LynxExceptionVO.builder();
  }

  public abstract int accumulator();

  public abstract String message();

  @Override
  public abstract String toString();

  @Override
  public String getMessage() {
    return message();
  }
}
