package com.pk.base;

/**
 * Created by pukai on 17/4/6.
 */

public class TreeNode<T> {
    public TreeNode leftChild;
    public TreeNode rightChild;
    public T data;
    public boolean isVisited;

    public TreeNode(T t) {
        data = t;
    }
}
