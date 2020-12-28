package com.clooker.solution.day.eight;

import com.clooker.solution.common.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class PartTwoSolution {

  public static Solution<Integer> solution(List<String> inputFileLines) {
    return Solution.<Integer>builder()
        .dayNumber(8)
        .part(Solution.Part.TWO)
        .solutionSupplier(
            () -> {
              List<List<String>> instructionsList = new ArrayList<>();
              for (int i = 0; i < inputFileLines.size(); i++) {
                final var instruction = inputFileLines.get(i);
                final var operation_argument = instruction.split(" ");
                final var operation = operation_argument[0];
                final var argument = operation_argument[1];

                if (operation.equals("jmp")) {
                  var instructions = new ArrayList<>(inputFileLines);
                  instructions.set(i, "nop " + argument);
                  instructionsList.add(instructions);
                } else if (operation.equals("nop")) {
                  var instructions = new ArrayList<>(inputFileLines);
                  instructions.set(i, "jmp " + argument);
                  instructionsList.add(instructions);
                }
              }

              for (List<String> instructions : instructionsList) {
                System.out.println("-".repeat(100));
                System.out.println(instructions);

                final Set<Integer> visitedIndices = new HashSet<>();

                var accumulator = 0;
                var didGetToEnd = false;
                for (int i = 0; i < instructions.size(); ) {
                  if (visitedIndices.contains(i)) {
                    System.out.println("oops " + i);
                    break;
                  }

                  visitedIndices.add(i);

                  if (i == instructions.size() - 1) didGetToEnd = true;

                  final var instruction = inputFileLines.get(i);
                  final var operation_argument = instruction.split(" ");
                  final var operation = operation_argument[0];
                  final var argument = operation_argument[1];
                  final var magnitude = Integer.parseInt(argument);

                  System.out.println("instruction " + instruction);
                  System.out.println("operation " + operation);
                  System.out.println("argument " + argument);
                  System.out.println("magnitude " + magnitude);
                  System.out.println();

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

                  System.out.println("accumulator: " + accumulator);
                }

                if (didGetToEnd) return accumulator;
              }

              throw new IllegalStateException("should have returned");
            })
        .build();
  }

  private PartTwoSolution() {}
}
