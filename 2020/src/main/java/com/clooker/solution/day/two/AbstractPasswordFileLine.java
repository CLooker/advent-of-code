package com.clooker.solution.day.two;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
abstract class AbstractPasswordFileLine {

  protected abstract String passwordFileLine();

  protected final String charToMatch() {
    final var passwordFileLine = passwordFileLine();
    final var indexOfColon = passwordFileLine.indexOf(":");
    return passwordFileLine.substring(indexOfColon - 1, indexOfColon);
  }

  protected final String password() {
    final var passwordFileLine = passwordFileLine();
    final var lastIndexOfSpace = passwordFileLine.lastIndexOf(" ");
    return passwordFileLine.substring(lastIndexOfSpace + 1);
  }

  protected final String policy() {
    final var passwordFileLine = passwordFileLine();
    final var indexOfSpace = passwordFileLine.indexOf(" ");
    return passwordFileLine.substring(0, indexOfSpace).replaceAll(" ", "");
  }
}
