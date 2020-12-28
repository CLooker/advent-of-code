package com.clooker.solution.day.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class Instructions {

  public static List<Instruction> from(List<String> instructionFileLines) {
    return instructionFileLines.parallelStream()
        .map(Instruction::from)
        .collect(Collectors.toList());
  }

  private Instructions() {}
}
