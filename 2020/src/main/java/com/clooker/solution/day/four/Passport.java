package com.clooker.solution.day.four;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonDeserialize(as = ImmutablePassport.class)
@JsonSerialize(as = ImmutablePassport.class)
interface Passport {

  static ImmutablePassport.Builder builder() {
    return ImmutablePassport.builder();
  }

  @JsonProperty("byr")
  Integer birthYear();

  @JsonProperty("eyr")
  Integer expirationYear();

  @JsonProperty("ecl")
  String eyeColor();

  @JsonProperty("hcl")
  String hairColor();

  @JsonProperty("hgt")
  String height();

  @JsonProperty("iyr")
  Integer issueYear();

  @JsonProperty("pid")
  String passportId();

  @Nullable
  @JsonProperty("cid")
  Integer countryId();
}
