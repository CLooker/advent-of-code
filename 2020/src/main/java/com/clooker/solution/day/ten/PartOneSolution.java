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
              final List<Long> jolts =
                  Stream.concat(Stream.of(0L), input.stream())
                      .sorted()
                      .collect(Collectors.toList());

              final List<Joltage> joltages = Joltages.of(jolts);

              final List<JoltageAdapter> joltageAdaptersWithoutDevice = JoltageAdapters.from(input);

              final List<JoltageAdapter> joltageAdapters =
                  JoltageAdapters.of(
                      joltageAdaptersWithoutDevice, Device.from(joltageAdaptersWithoutDevice));

              final Set<JoltageAdapter> matches = new HashSet<>();
              final List<Long> joltsDifferences = new ArrayList<>();

              joltages.forEach(
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

  private PartOneSolution() {}
}
