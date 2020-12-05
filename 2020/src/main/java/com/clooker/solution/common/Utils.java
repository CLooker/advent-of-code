package com.clooker.solution.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utils {

  private static final Logger logger = Logger.getLogger(Utils.class.getName());

  public static <E> Map<E, Long> elementToFrequency(Collection<E> elements) {
    return elements.parallelStream()
        .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
  }

  public static Stream<String> parseInput(Path inputPath) {
    try {
      return Files.lines(inputPath);
    } catch (IOException e) {
      logger.severe("failed parsing input " + e);
      return Stream.of();
    }
  }

  private Utils() {}
}
