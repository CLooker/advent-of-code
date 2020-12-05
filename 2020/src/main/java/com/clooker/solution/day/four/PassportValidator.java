package com.clooker.solution.day.four;

import com.clooker.solution.common.Validator;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

final class PassportValidator implements Validator<Passport> {

  @Override
  public boolean isValid(Passport passport) {
    return Stream.<Predicate<Passport>>of(
            this::hasValidBirthYear,
            this::hasValidExpirationYear,
            this::hasValidEyeColor,
            this::hasValidHairColor,
            this::hasValidHeight,
            this::hasValidIssueYear,
            this::hasValidPassportId)
        .parallel()
        .allMatch(passportPredicate -> passportPredicate.test(passport));
  }

  private boolean hasValidHeight(Passport passport) {
    final boolean hasValidHeightSuffix =
        Stream.of("cm", "in")
            .anyMatch(validHeightSuffix -> passport.height().endsWith(validHeightSuffix));
    if (!hasValidHeightSuffix) return false;

    int height;
    try {
      height = Integer.parseInt(passport.height().replace("cm", "").replace("in", ""));
    } catch (Exception e) {
      return false;
    }

    return (passport.height().endsWith("cm") && height >= 150 && height <= 193)
        || (passport.height().endsWith("in") && height >= 59 && height <= 76);
  }

  private boolean hasValidPassportId(Passport passport) {
    return passport.passportId().length() == 9
        && Pattern.compile("^[0-9]+$").matcher(passport.passportId()).matches();
  }

  private boolean hasValidBirthYear(Passport passport) {
    return passport.birthYear() >= 1920 && passport.birthYear() <= 2002;
  }

  private boolean hasValidExpirationYear(Passport passport) {
    return passport.expirationYear() >= 2020 && passport.expirationYear() <= 2030;
  }

  private boolean hasValidEyeColor(Passport passport) {
    return Stream.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        .anyMatch(eyeColor -> eyeColor.equals(passport.eyeColor()));
  }

  private boolean hasValidHairColor(Passport passport) {
    return passport.hairColor().contains("#")
        && passport.hairColor().length() == 7
        && Pattern.compile("^[0-9a-f]+$").matcher(passport.hairColor().replace("#", "")).matches();
  }

  private boolean hasValidIssueYear(Passport passport) {
    return passport.issueYear() >= 2010 && passport.issueYear() <= 2020;
  }
}
