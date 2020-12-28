package com.clooker.solution.day.seven;

import com.clooker.solution.common.Tree;

import java.util.ArrayList;
import java.util.List;

public class BagNode implements Tree.Node<Bag> {

  private final List<Tree.Node<Bag>> children = new ArrayList<>();

  public BagNode(Bag bag) {}

  @Override
  public boolean addChild(Bag bag) {
    children.add(new BagNode(bag));
    return true;
  }

  @Override
  public List<Tree.Node<Bag>> children() {
    return children;
  }
}
