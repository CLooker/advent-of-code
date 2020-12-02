package com.clooker.solution.common;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public final class Pairs {

  public static <E> Optional<Pair<E>> findFirst(List<E> elements, BiPredicate<E, E> biPredicate) {

    return elements.parallelStream()
        .map(
            el ->
                elements.parallelStream()
                    .filter(el2 -> biPredicate.test(el, el2))
                    .findFirst()
                    .map(el2 -> Pair.<E>builder().first(el).second(el2).build())
                    .orElse(null))
        .filter(Objects::nonNull)
        .findFirst();
  }

  private Pairs() {}
}
