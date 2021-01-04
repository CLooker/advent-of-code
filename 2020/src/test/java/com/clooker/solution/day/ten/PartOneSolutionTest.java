package com.clooker.solution.day.ten;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class PartOneSolutionTest {

  @Test
  void
      joltage_adapter_output_returns_optional_wrapping_its_rating_when_input_is_within_less_than_3() {
    final Joltage rating = Joltage.builder().jolts(10L).build();

    final var joltageAdapter = JoltageAdapter.builder().rating(rating).build();

    final Joltage lessThan3FromRating = Joltage.builder().jolts(rating.jolts() - 3).build();

    assertEquals(joltageAdapter.rating(), joltageAdapter.output(lessThan3FromRating).orElse(null));
  }

  @Test
  void
      joltage_adapter_output_returns_optional_wrapping_null_when_input_is_not_within_less_than_3() {
    final Joltage rating = Joltage.builder().jolts(10L).build();

    final var joltageAdapter = JoltageAdapter.builder().rating(rating).build();

    final Joltage lessThan4FromRating = Joltage.builder().jolts(rating.jolts() - 4).build();

    assertNull(joltageAdapter.output(lessThan4FromRating).orElse(null));
  }

  @Test
  void device_wraps_joltage_adapter_that_is_3_more_than_max_of_input() {
    final JoltageAdapter maxInput =
        JoltageAdapter.builder().rating(Joltage.builder().jolts(10L).build()).build();

    final JoltageAdapter minInput =
        JoltageAdapter.builder()
            .rating(Joltage.builder().jolts(maxInput.rating().jolts() - 1).build())
            .build();

    final List<JoltageAdapter> joltageAdapter = List.of(minInput, maxInput);

    final var device = Device.from(joltageAdapter);

    final JoltageAdapter with3MoreThanMax =
        JoltageAdapter.builder()
            .rating(Joltage.builder().jolts(maxInput.rating().jolts() + 3).build())
            .build();

    assertEquals(with3MoreThanMax, device.joltageAdapter());
  }
}
