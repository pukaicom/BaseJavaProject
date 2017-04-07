package com.pk.base;

/**
 * Created by pukai on 17/4/7.
 */

public class GraphClient {
    public static void main(String[] args) {
        Node<Integer> top = new Node<>();
        top.data = 1;
        Node<Integer> node2 = new Node<>();
        node2.data = 2;
        Node<Integer> node3 = new Node<>();
        node3.data = 3;
        Node<Integer> node4 = new Node<>();
        node4.data = 4;
        Node<Integer> node5 = new Node<>();
        node5.data = 5;
        Node<Integer> node6 = new Node<>();
        node6.data = 6;
        Node<Integer> node7 = new Node<>();
        node7.data = 7;
        Node<Integer> node8 = new Node<>();
        node8.data = 8;

        top.neighborNodes.add(node2);
        top.neighborNodes.add(node3);
        node2.neighborNodes.add(node4);
        node2.neighborNodes.add(node5);
        node2.neighborNodes.add(top);

        node4.neighborNodes.add(node2);
        node4.neighborNodes.add(node8);
        node5.neighborNodes.add(node2);
        node5.neighborNodes.add(node8);

        node8.neighborNodes.add(node4);
        node8.neighborNodes.add(node5);

        node3.neighborNodes.add(top);
        node3.neighborNodes.add(node6);
        node3.neighborNodes.add(node7);

        node6.neighborNodes.add(node3);
        node6.neighborNodes.add(node7);

        node7.neighborNodes.add(node3);
        node7.neighborNodes.add(node6);
        DFSearchRecursive(top);

        DFSearch(top);

        BFSearch(top);

    }

    /**
     * 图的遍历 深度优先 DFS 递归实现
     *
     * @param top
     */
    public static void DFSearchRecursive(Node<Integer> top) {
        top.isVisited = true;
        System.out.print("  " + top.data);
        if (top.hasNeighbors()) {
            for (int i = 0; i < top.neighborNodes.size(); i++) {
                if (!top.neighborNodes.get(i).isVisited) {
                    DFSearchRecursive(top.neighborNodes.get(i));
                }
            }
        }
    }

    /**
     * 图的遍历 深度优先 DFS 非递归实现
     *
     * @param top
     */
    public static void DFSearch(Node<Integer> top) {
        Stack<Node<Integer>> stack = new Stack<>();
        stack.push(top);
        while (!stack.isEmpty()) {
            Node<Integer> temp = stack.getTop();
            System.out.print(temp.data + "  ");
            temp.isVisited = true;
            stack.pop();
            if (temp.hasNeighbors()) {
                int size = temp.neighborNodes.size();
                for (int i = size - 1; i >= 0; i--) {
                    Node<Integer> node = temp.neighborNodes.get(i);
                    if (!node.isVisited && !stack.conatinsObject(node)) {
                        stack.push(node);
                    }
                }
            }
        }
    }

    /**
     * 图的遍历 广度优先 BFS
     *
     * @param top
     */
    public static void BFSearch(Node<Integer> top) {
        Queue<Node<Integer>> queue = new Queue<>();
        queue.inQueue(top);
        while (!queue.isEmpty()) {
            Node<Integer> temp = queue.outQueue();
            System.out.print(temp.data + "   ");
            temp.isVisited = true;
            if (temp.hasNeighbors()) {
                for (Node<Integer> node : temp.neighborNodes) {
                    if (!node.isVisited && !queue.conatinsObject(node)) {
                        queue.inQueue(node);
                    }
                }
            }
        }
    }

}

