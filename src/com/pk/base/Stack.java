package com.pk.base;

import java.util.ArrayList;

/**
 * Created by pukai on 17/4/6.
 */

public class Stack<T> {
    ArrayList<T> list = new ArrayList<>();

    public void pop() {
        int size = list.size();
        if (size > 0) {
            list.remove(size - 1);
        }
    }

    public void push(T t) {
        list.add(t);
    }

    public T getTop() {
        int size = list.size();
        if (size > 0) {
            return list.get(size - 1);
        } else {
            return null;
        }
    }

    public boolean conatinsObject(T t) {
        return list.contains(t);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
