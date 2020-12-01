package common;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public final class Pairs {

  public static <E> Optional<Pair<E>> findFirst(List<E> elements, BiPredicate<E, E> biPredicate) {

    return elements.parallelStream()
        .map(
            el -> {
              var matchingEl =
                  elements.parallelStream()
                      .filter(e -> biPredicate.test(el, e))
                      .findFirst()
                      .orElse(null);

              return matchingEl == null
                  ? null
                  : Pair.<E>builder().first(el).second(matchingEl).build();
            })
        .filter(Objects::nonNull)
        .findFirst();
  }

  private Pairs() {}
}
