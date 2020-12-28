package com.clooker.solution.day.eight;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class Lynx {

  public static int run(List<Instruction> instructions) throws LynxException {
    final Set<Integer> visitedIndices = new HashSet<>();

    var accumulator = 0;
    for (int i = 0; i < instructions.size(); ) {
      if (visitedIndices.contains(i)) {
        throw LynxException.builder()
            .accumulator(accumulator)
            .message("Looks like we have an infinite loop...")
            .build();
      }

      visitedIndices.add(i);

      final var instruction = instructions.get(i);
      final Instruction.Operation operation = instruction.operation();
      final int magnitude = instruction.magnitude();

      switch (operation) {
        case ACC:
          {
            accumulator += magnitude;
            ++i;
            break;
          }
        case JMP:
          {
            i += magnitude;
            break;
          }
        case NOP:
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
  }
  
  private Lynx() {}
}
