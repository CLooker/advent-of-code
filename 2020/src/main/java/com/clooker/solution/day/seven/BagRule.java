package com.clooker.solution.day.seven;

import org.immutables.value.Value;

import java.util.*;
import java.util.stream.Collectors;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
interface BagRule {

  BagColor container();

  List<BagColorAndCount> contained();

  static BagRuleVO.Builder builder() {
    return BagRuleVO.builder();
  }

  static BagRule from(String bagRuleFileLine) {
    Objects.requireNonNull(bagRuleFileLine);

    final var lineSplitOnSpace = bagRuleFileLine.split(" ");

    final var colorModifierAndColorArr =
        Arrays.stream(lineSplitOnSpace)
            .takeWhile(lineSegment -> !lineSegment.contains("bag"))
            .toArray(String[]::new);

    final var container =
        BagColor.builder()
            .colorModifier(colorModifierAndColorArr[0])
            .color(colorModifierAndColorArr[1])
            .build();

    final var containedStr =
        Arrays.stream(lineSplitOnSpace)
            .dropWhile(lineSegment -> !lineSegment.contains("contain"))
            .skip(1)
            .map(s -> s.replaceAll("\\.", ""))
            .collect(Collectors.joining(" "));

    final List<BagColorAndCount> contained =
        containedStr.contains("no other bags")
            ? Collections.emptyList()
            : Arrays.stream(containedStr.split(","))
                .map(String::trim)
                .map(
                    s -> {
                      final var indexOfSpace = s.indexOf(" ");
                      final var count = Integer.parseInt(s.substring(0, indexOfSpace));
                      final var x_colorModifier_color_freq = s.substring(indexOfSpace).split(" ");
                      final var colorModifier = x_colorModifier_color_freq[1];
                      final var color = x_colorModifier_color_freq[2];
                      final var bgvo =
                          BagColor.builder().colorModifier(colorModifier).color(color).build();
                      return BagColorAndCount.builder().bagColor(bgvo).count(count).build();
                    })
                .collect(Collectors.toList());

    return builder().container(container).contained(contained).build();
  }
}
