package com.clooker.solution.day.seven;

import com.clooker.solution.common.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class BagTree implements Tree<Bag> {

  public final Node<Bag> root;

  private final List<Node<Bag>> prevLeaves = new ArrayList<>();

  public BagTree(Node<Bag> root) {
    this.root = root;
  }

  @Override
  public Node<Bag> root() {
    return root;
  }

  @Override
  public int size() {
    final var result = new AtomicInteger(0);
    findSize(root, result);
    return result.get();
  }

  @Override
  public List<Node<Bag>> leaves() {
    final List<Node<Bag>> result = new ArrayList<>();
    if (prevLeaves.isEmpty()) {
      findLeaves(root, result);
    } else {
      findLeaves(prevLeaves, result);
    }
    prevLeaves.clear();
    prevLeaves.addAll(result);
    return result;
  }

  private void findSize(Node<Bag> bagNode, AtomicInteger count) {
    bagNode.children().parallelStream()
        .forEach(
            bn -> {
              count.getAndIncrement();
              findSize(bn, count);
            });
  }

  private void findLeaves(Node<Bag> bagNode, List<Node<Bag>> result) {
    final var children = bagNode.children();
    if (children.isEmpty()) result.add(bagNode);
    else {
      children.forEach(bn -> findLeaves(bn, result));
    }
  }

  private void findLeaves(List<Node<Bag>> prevLeaves, List<Node<Bag>> result) {
    prevLeaves.forEach(bagNode -> findLeaves(bagNode, result));
  }
}
