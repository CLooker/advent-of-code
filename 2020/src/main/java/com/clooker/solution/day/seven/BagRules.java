package com.clooker.solution.day.seven;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class BagRules {

  private final Map<Bag, BagRule> bagRuleByBag;

  public static BagRules from(List<String> bagRuleFileLines) {
    return new BagRules(
        bagRuleFileLines.parallelStream().map(BagRule::from).toArray(BagRule[]::new));
  }

  public BagRules(BagRule... bagRules) {
    this.bagRuleByBag =
        Stream.of(bagRules).parallel().collect(Collectors.toMap(BagRule::container, x -> x));
  }

  public Optional<BagRule> bagRule(Bag bag) {
    return Optional.ofNullable(bagRuleByBag.get(bag));
  }

  public Set<Bag> containers() {
    return bagRuleByBag.keySet();
  }

  public Set<Bag> walkContainees(Bag container) {
    final Set<Bag> result = ConcurrentHashMap.newKeySet();
    walk(container, result);
    return result;
  }

  private void walk(Bag bag, Set<Bag> visitedBags) {
    bagRule(bag)
        .ifPresent(
            bagRule ->
                bagRule.containees().stream()
                    .map(BagAndCount::bag)
                    .forEach(
                        b -> {
                          visitedBags.add(b);
                          walk(b, visitedBags);
                        }));
  }
}
