package com.clooker.solution.day.eight;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*VO")
interface Instruction {

  enum Operation {
    ACC,
    JMP,
    NOP
  }

  int magnitude();

  Operation operation();

  static InstructionVO.Builder builder() {
    return InstructionVO.builder();
  }

  static Instruction from(String instructionFileLine) {
    final var operation_argument = instructionFileLine.split(" ");
    final var operation = Operation.valueOf(operation_argument[0].toUpperCase());
    final var magnitude = Integer.parseInt(operation_argument[1]);
    return builder().magnitude(magnitude).operation(operation).build();
  }
}
