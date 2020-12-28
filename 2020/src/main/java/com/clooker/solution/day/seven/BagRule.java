package com.clooker.solution.day.seven;

import org.immutables.value.Value;

import java.util.*;
import java.util.stream.Collectors;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
interface BagRule {

  Bag container();

  List<BagAndCount> containees();

  static BagRuleVO.Builder builder() {
    return BagRuleVO.builder();
  }

  static BagRule from(String bagRuleFileLine) {
    Objects.requireNonNull(bagRuleFileLine);

    final String[] lineSplitOnSpace = bagRuleFileLine.split(" ");

    final List<String> colorModifier_color =
        Arrays.stream(lineSplitOnSpace)
            .takeWhile(lineSegment -> !lineSegment.contains("bag"))
            .collect(Collectors.toList());

    final Bag container =
        Bag.builder()
            .colorModifier(colorModifier_color.get(0))
            .color(colorModifier_color.get(1))
            .build();

    final var containeesStr =
        Arrays.stream(lineSplitOnSpace)
            .dropWhile(lineSegment -> !lineSegment.contains("contain"))
            .skip(1)
            .map(s -> s.replaceAll("\\.", ""))
            .collect(Collectors.joining(" "));

    final List<BagAndCount> containees =
        containeesStr.contains("no other bags")
            ? Collections.emptyList()
            : Arrays.stream(containeesStr.split(","))
                .map(String::trim)
                .map(
                    s -> {
                      final int indexOfSpace = s.indexOf(" ");

                      final int count = Integer.parseInt(s.substring(0, indexOfSpace));

                      final String[] colorModifier_color_unused =
                          s.substring(indexOfSpace + 1).split(" ");
                      final String colorModifier = colorModifier_color_unused[0];
                      final String color = colorModifier_color_unused[1];
                      final Bag bag =
                          Bag.builder().colorModifier(colorModifier).color(color).build();

                      return BagAndCount.builder().bag(bag).count(count).build();
                    })
                .collect(Collectors.toList());

    return builder().container(container).containees(containees).build();
  }
}
