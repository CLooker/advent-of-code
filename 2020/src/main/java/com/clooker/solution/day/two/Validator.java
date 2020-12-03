package com.clooker.solution.day.two;

@FunctionalInterface
interface Validator<T> {

  boolean isValid(T t);

}
