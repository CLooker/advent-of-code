package com.clooker.solution.day.ten;

import com.clooker.solution.common.Solution;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class PartOneSolution {

  public static Solution<Long> solution(List<Long> input) {
    return Solution.<Long>builder()
        .dayNumber(10)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              final List<JoltageAdapter> joltageAdapters =
                  Stream.concat(
                          input.stream(),
                          input.stream().max(Long::compare).map(jolts -> jolts + 3L).stream())
                      .sorted()
                      .map(JoltageAdapter::fromJolts)
                      .collect(Collectors.toList());

              final Stream<Joltage> joltagesToAdapt =
                  Stream.concat(Stream.of(0L), input.stream())
                      .sorted()
                      .map(jolts -> Joltage.builder().jolts(jolts).build());

              final Set<JoltageAdapter> matches = new HashSet<>();
              final List<Long> joltsDifferences = new ArrayList<>();

              joltagesToAdapt.forEach(
                  joltageToAdapt -> {
                    final JoltageAdapter match =
                        joltageAdapters.parallelStream()
                            .filter(
                                joltageAdapter ->
                                    !matches.contains(joltageAdapter)
                                        && joltageAdapter.output(joltageToAdapt).orElse(null)
                                            != null)
                            .findFirst()
                            .orElseThrow(
                                () ->
                                    new IllegalStateException(
                                        "no JoltageAdapter matches joltage " + joltageToAdapt));

                    final long joltsDifference = match.rating().jolts() - joltageToAdapt.jolts();

                    matches.add(match);
                    joltsDifferences.add(joltsDifference);
                  });

              final long onesCount = joltsDifferences.stream().filter(d -> d == 1L).count();
              final long threesCount = joltsDifferences.stream().filter(d -> d == 3L).count();

              return onesCount * threesCount;
            })
        .build();
  }

  private PartOneSolution() {}
}
