package common;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
    visibility = Value.Style.ImplementationVisibility.PUBLIC,
    overshadowImplementation = true)
public interface Pair<E> {

  static <E> ImmutablePair.Builder<E> builder() {
    return ImmutablePair.builder();
  }

  E first();

  E second();
}
