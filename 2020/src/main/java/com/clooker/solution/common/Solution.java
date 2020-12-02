package com.clooker.solution.common;

import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.function.Supplier;
import java.util.logging.Logger;

@Value.Immutable
@Value.Style(visibility = Value.Style.ImplementationVisibility.PUBLIC)
public abstract class Solution<E> {

  private static final Logger logger = Logger.getLogger(Solution.class.getName());

  public static <E> ImmutableSolution.Builder<E> builder() {
    return ImmutableSolution.builder();
  }

  public enum Part {
    ONE,
    TWO
  }

  abstract int dayNumber();

  abstract Part part();

  abstract Supplier<E> solutionSupplier();

  @Value.Check
  protected void check() {
    Preconditions.checkState(
        dayNumber() >= 1 && dayNumber() <= 25, "dayNumber must be between 0 and 25");
  }

  public final void logSolution() {
    logger.info("solution for day " + dayNumber() + ", part " + part() + ": " + solution());
  }

  E solution() {
    return solutionSupplier().get();
  }
}
