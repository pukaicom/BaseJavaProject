package com.pk.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pukai on 17/4/7.
 */

public class Node<T> {
    public List<Node<T>> neighborNodes = new ArrayList<>();
    public T data;
    public boolean isVisited;

    public boolean hasNeighbors() {
        return !neighborNodes.isEmpty();
    }
}
