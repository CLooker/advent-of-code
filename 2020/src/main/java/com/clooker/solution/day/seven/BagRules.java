package com.clooker.solution.day.seven;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class BagRules {

  private final Map<BagColor, List<BagColorAndCount>> bagRuleMap;

  public static BagRules from(List<String> bagRuleFileLines) {
    return new BagRules(
        bagRuleFileLines.parallelStream().map(BagRule::from).toArray(BagRule[]::new));
  }

  public BagRules(BagRule... bagRules) {
    this.bagRuleMap =
        Stream.of(bagRules)
            .parallel()
            .collect(Collectors.toMap(BagRule::container, BagRule::contained));
  }

  Set<BagColor> containers() {
    return bagRuleMap.keySet();
  }

  Set<BagColor> containees(BagColor container) {
    final Set<BagColor> result = ConcurrentHashMap.newKeySet();
    visit(container, result);
    return result;
  }

  private void visit(BagColor bagColor, Set<BagColor> visitedBagColors) {
    bagRuleMap.getOrDefault(bagColor, Collections.emptyList()).parallelStream()
        .map(BagColorAndCount::bagColor)
        .forEach(
            bc -> {
              visitedBagColors.add(bc);
              visit(bc, visitedBagColors);
            });
  }
}
