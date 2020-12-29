package com.clooker.solution.day.six;

import com.clooker.solution.common.Solution;
import com.clooker.solution.common.Utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

final class Solutions {

  private static final List<String> inputFileLines =
      Utils.parseInput(Paths.get("src", "main", "resources", "6.input.txt"))
          .collect(Collectors.toList());

  private static final Solution<Integer> partOneSolution =
      Solution.<Integer>builder()
          .dayNumber(6)
          .part(Solution.Part.ONE)
          .solutionSupplier(
              () ->
                  responseGroups(inputFileLines).parallelStream()
                      .map(responseGroup -> charToFreq(responseGroup).keySet().size())
                      .reduce(0, Integer::sum))
          .build();

  private static final Solution<Long> partTwoSolution =
      Solution.<Long>builder()
          .dayNumber(6)
          .part(Solution.Part.TWO)
          .solutionSupplier(
              () ->
                  responseGroups(inputFileLines).parallelStream()
                      .map(
                          responseGroup ->
                              charToFreq(responseGroup).values().parallelStream()
                                  .filter(freq -> freq == responseGroup.size())
                                  .count())
                      .reduce(0L, Long::sum))
          .build();

  public static void main(String[] args) {
    List.of(partOneSolution, partTwoSolution).forEach(Solution::logSolution);
  }

  private static List<List<String>> responseGroups(List<String> inputFileLines) {
    List<List<String>> responseGroups = new ArrayList<>();
    for (String inputFileLine : inputFileLines) {
      if (responseGroups.isEmpty()) {
        List<String> responseGroup = new ArrayList<>();
        responseGroup.add(inputFileLine);
        responseGroups.add(responseGroup);
        continue;
      }

      if (inputFileLine.length() == 0) {
        List<String> responseGroup = new ArrayList<>();
        responseGroups.add(responseGroup);
        continue;
      }

      var prevResponseGroup = responseGroups.get(responseGroups.size() - 1);
      prevResponseGroup.add(inputFileLine);
    }
    return responseGroups;
  }

  private static Map<Character, Long> charToFreq(List<String> strings) {
    return strings.parallelStream()
        .flatMap(s -> s.chars().mapToObj(i -> (char) i))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }
}
