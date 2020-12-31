package com.clooker.solution.day.ten;

import com.clooker.solution.common.Solution;

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
              final List<JoltageAdapter> joltageAdapters = joltageAdapters(input);

              final List<Joltage> joltagesToAdapt =
                  Stream.concat(Stream.of(0L), input.stream())
                      .sorted()
                      .map(jolts -> Joltage.builder().jolts(jolts).build())
                      .collect(Collectors.toList());

              final Set<JoltageAdapter> matches = new HashSet<>();
              final List<Long> joltsDifferences = new ArrayList<>();

              joltagesToAdapt.forEach(
                  joltage -> {
                    final JoltageAdapter match =
                        joltageAdapters.stream()
                            .filter(
                                joltageAdapter ->
                                    !matches.contains(joltageAdapter)
                                        && joltageAdapter.output(joltage).orElse(null) != null)
                            .findFirst()
                            .orElseThrow(
                                () ->
                                    new IllegalStateException(
                                        "no JoltageAdapter matches joltage " + joltage));

                    final long joltsDifference = match.rating().jolts() - joltage.jolts();

                    matches.add(match);
                    joltsDifferences.add(joltsDifference);
                  });

              final long onesCount = joltsDifferences.stream().filter(d -> d == 1L).count();
              final long threesCount = joltsDifferences.stream().filter(d -> d == 3L).count();

              return onesCount * threesCount;
            })
        .build();
  }

  static List<JoltageAdapter> joltageAdapters(List<Long> joltsList) {
    final List<JoltageAdapter> joltageAdaptersWithoutDevice = JoltageAdapters.from(joltsList);

    return JoltageAdapters.of(
        joltageAdaptersWithoutDevice, Device.from(joltageAdaptersWithoutDevice));
  }

  private PartOneSolution() {}
}
