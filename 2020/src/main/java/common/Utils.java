package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class Utils {

  private static final Logger logger = Logger.getLogger(Utils.class.getName());

  public static <E> Map<E, Long> elementToFrequency(Collection<E> elements) {
    return elements.parallelStream()
        .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
  }

  public static List<String> parseInput(Path inputPath) {
    try {
      return Files.lines(inputPath).parallel().collect(Collectors.toList());
    } catch (IOException e) {
      logger.severe("failed parsing input " + e);
      return List.of();
    }
  }

  private Utils() {}
}
