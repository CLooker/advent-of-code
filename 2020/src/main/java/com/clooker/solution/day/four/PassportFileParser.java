package com.clooker.solution.day.four;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;
import java.util.stream.Collectors;

final class PassportFileParser {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static List<Passport> passports(List<String> passportFileLines) {
    return passportLines(passportFileLines).parallelStream()
        .map(
            passportLine ->
                passportJson(entries(passportLine))
                    .flatMap(PassportFileParser::passport)
                    .orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private static List<String> passportLines(List<String> passportFileLines) {
    List<List<String>> passportLineGroups = new ArrayList<>();
    passportFileLines.forEach(
        passportFileLine -> {
          if (passportLineGroups.size() == 0) {
            List<String> passportFileLineGroup = new ArrayList<>();
            passportFileLineGroup.add(passportFileLine);
            passportLineGroups.add(passportFileLineGroup);
            return;
          }

          List<String> lastPassportFileLineGroup =
              passportLineGroups.get(passportLineGroups.size() - 1);
          String lastPassportFileLine =
              lastPassportFileLineGroup.get(lastPassportFileLineGroup.size() - 1);
          if (lastPassportFileLine.length() == 0) {
            List<String> passportFileLineGroup = new ArrayList<>();
            passportFileLineGroup.add(passportFileLine);
            passportLineGroups.add(passportFileLineGroup);
          } else {
            lastPassportFileLineGroup.add(passportFileLine);
          }
        });

    return passportLineGroups.parallelStream()
        .map(
            passportLineGroup ->
                passportLineGroup.parallelStream()
                    .map(str -> Arrays.asList(str.split(" ")))
                    .flatMap(List::stream)
                    .map(str -> str.replace("\n", ""))
                    .filter(str -> str.length() > 0)
                    .collect(Collectors.joining(" ")))
        .collect(Collectors.toList());
  }

  private static List<Map.Entry<String, String>> entries(String passportLine) {
    return Arrays.stream(passportLine.split(" "))
        .parallel()
        .map(
            keyValuePairDelimitedOnColon -> {
              final String[] keyValuePair = keyValuePairDelimitedOnColon.split(":");
              return Map.entry(keyValuePair[0], keyValuePair[1]);
            })
        .collect(Collectors.toList());
  }

  private static Optional<String> passportJson(List<Map.Entry<String, String>> entries) {
    ObjectNode rootNode = objectMapper.createObjectNode();
    entries.forEach(entry -> rootNode.put(entry.getKey(), entry.getValue()));

    try {
      final String json =
          objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
      return Optional.of(json);
    } catch (JsonProcessingException e) {
      return Optional.empty();
    }
  }

  private static Optional<Passport> passport(String json) {
    try {
      final var passport = objectMapper.readValue(json, Passport.class);
      return Optional.of(passport);
    } catch (JsonProcessingException e) {
      return Optional.empty();
    }
  }

  private PassportFileParser() {}
}
