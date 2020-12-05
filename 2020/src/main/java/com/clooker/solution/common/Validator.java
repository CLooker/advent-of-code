package com.clooker.solution.common;

@FunctionalInterface
public interface Validator<T> {

  boolean isValid(T t);

}
