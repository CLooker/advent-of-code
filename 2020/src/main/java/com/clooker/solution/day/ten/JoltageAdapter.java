package com.clooker.solution.day.ten;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
abstract class JoltageAdapter {

  public abstract Joltage rating();

  public final Optional<Joltage> output(Joltage joltage) {
    final boolean canAdapt = rating().jolts() - joltage.jolts() <= 3;
    return Optional.ofNullable(canAdapt ? rating() : null);
  }
}
