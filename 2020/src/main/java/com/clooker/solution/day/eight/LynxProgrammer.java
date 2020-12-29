package com.clooker.solution.day.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class LynxProgrammer {

  public static int fixBug(List<Instruction> instructions) {
    return switchOperations(instructions, Instruction.Operation.JMP, Instruction.Operation.NOP)
        .parallelStream()
        .map(
            ins -> {
              try {
                return Lynx.run(ins);
              } catch (LynxException e) {
                return null;
              }
            })
        .filter(Objects::nonNull)
        .findAny()
        .orElseThrow(() -> new IllegalStateException("bug still not fixed!!"));
  }

  private static List<List<Instruction>> switchOperations(
      List<Instruction> instructions,
      Instruction.Operation switchableOp1,
      Instruction.Operation switchableOp2) {
    final List<List<Instruction>> instructionLists = new ArrayList<>();

    for (int i = 0; i < instructions.size(); i++) {
      final var instruction = instructions.get(i);
      final var operation = instruction.operation();

      final var instructionsList = new ArrayList<>(instructions);
      if (operation == switchableOp1) {
        instructionsList.set(
            i,
            Instruction.builder().from(instruction).operation(Instruction.Operation.NOP).build());
        instructionLists.add(instructionsList);
      } else if (operation == switchableOp2) {
        instructionsList.set(
            i,
            Instruction.builder().from(instruction).operation(Instruction.Operation.JMP).build());
        instructionLists.add(instructionsList);
      }
    }

    return instructionLists;
  }

  private LynxProgrammer() {}
}
