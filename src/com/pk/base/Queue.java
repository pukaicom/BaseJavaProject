package com.pk.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pukai on 17/4/6.
 */

public class Queue<T> {
    private List<T> list = new ArrayList<>();

    public void inQueue(T t) {
        list.add(t);
    }

    public T outQueue() {
        int size = list.size();
        if (size > 0) {
            T t = list.get(0);
            list.remove(0);
            return t;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean conatinsObject(T t) {
        return list.contains(t);
    }
}
