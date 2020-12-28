package com.clooker.solution.common;

import java.util.List;

public interface Tree<E> {

  Node<E> root();

  int size();

  List<Node<E>> leaves();

  interface Node<E> {

    boolean addChild(E e);

    List<Node<E>> children();
  }
}
