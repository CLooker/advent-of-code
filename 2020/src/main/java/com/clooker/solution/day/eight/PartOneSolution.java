package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class PartOneSolution {

  public static Solution<Integer> solution(List<String> inputFileLines) {
    return Solution.<Integer>builder()
        .dayNumber(8)
        .part(Solution.Part.ONE)
        .solutionSupplier(
            () -> {
              final Set<Integer> visitedIndices = new HashSet<>();

              var accumulator = 0;
              for (int i = 0; i < inputFileLines.size(); ) {
                if (visitedIndices.contains(i)) {
                  return accumulator;
                }

                visitedIndices.add(i);

                final var instruction = inputFileLines.get(i);
                final var operation_argument = instruction.split(" ");
                final var operation = operation_argument[0];
                final var argument = operation_argument[1];
                final var magnitude = Integer.parseInt(argument);

                switch (operation) {
                  case ("acc"):
                    {
                      accumulator += magnitude;
                      ++i;
                      break;
                    }
                  case "jmp":
                    {
                      i += magnitude;
                      break;
                    }
                  case ("nop"):
                    {
                      ++i;
                      break;
                    }
                  default:
                    {
                      throw new IllegalStateException("unexpected operation " + operation);
                    }
                }
              }

              return accumulator;
            })
        .build();
  }

  private PartOneSolution() {}
}
