package com.clooker.solution.day.two;

import com.clooker.solution.common.Validator;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
abstract class AbstractPasswordFileLineValidator {

  protected abstract PasswordFileLine passwordFileLine();

  protected abstract Validator<PasswordFileLine> validator();

  public final boolean isValid() {
    return validator().isValid(passwordFileLine());
  }
}
