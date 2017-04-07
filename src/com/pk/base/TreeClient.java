package com.pk.base;

/**
 * Created by pukai on 17/4/6.
 */

public class TreeClient {
    public static TreeNode<Integer> root = new TreeNode<>(0);

    public static void main(String[] args) {
        createTreeNode();
/*        preRoot(root);
        midRoot(root);
        postRoot(root);*/
        levelRoot(root);
        System.out.println("树的高度为   " + getTreeHeight(root));
    }

    public static void createTreeNode() {
        TreeNode<Integer> temp = root;
        for (int i = 1; i < 5; i++) {
            TreeNode<Integer> left = new TreeNode<>(i * -1);
            TreeNode<Integer> right = new TreeNode<>(i);
            temp.leftChild = left;
            temp.rightChild = right;
            temp = left;
        }
    }

    /**
     * 二叉树 先根序  遍历
     *
     * @param root 根节点
     */
    public static void preRoot(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<Integer> top = stack.getTop();
            System.out.println(top.data);
            stack.pop();
            if (top.rightChild != null) {
                stack.push(top.rightChild);
            }
            if (top.leftChild != null) {
                stack.push(top.leftChild);
            }
        }
    }

    /**
     * 二叉树 中根序  遍历
     *
     * @param root
     */
    public static void midRoot(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (root != null && root.leftChild != null) {
                root = root.leftChild;
                stack.push(root);
            }
            root = stack.getTop();
            stack.pop();
            if (root != null) {
                System.out.println(root.data);
                root = root.rightChild;
                stack.push(root);
            }
        }
    }

    /**
     * 二叉树 后根序 遍历
     *
     * @param root
     */
    public static void postRoot(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (root != null && root.leftChild != null) {
                root = root.leftChild;
                stack.push(root);
            }
            root = stack.getTop();
            if (root != null) {
                if (root.rightChild != null && !root.rightChild.isVisited) {
                    stack.push(root.rightChild);
                    root = root.rightChild;
                } else {
                    root.isVisited = true;
                    System.out.println(root.data);
                    stack.pop();
                    root = null;
                    stack.push(root);
                }
            } else {
                stack.pop();
            }
        }
    }

    /**
     * 二叉树 层次 遍历
     *
     * @param root
     */
    public static void levelRoot(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> queue = new Queue<>();
        queue.inQueue(root);
        while (!queue.isEmpty()) {
            root = queue.outQueue();
            System.out.println(root.data);
            if (root.leftChild != null) {
                queue.inQueue(root.leftChild);
            }
            if (root.rightChild != null) {
                queue.inQueue(root.rightChild);
            }
        }
    }

    /**
     * 递归求解树的深度
     *
     * @param root
     * @return
     */
    public static int getTreeHeight(TreeNode<Integer> root) {
        if (root == null) {
            return 0;  //根节点为null 返回0；
        } else {
            /* 递归 获取左子树和右子数的深度的交大值，并且+1; */
            return Math.max(getTreeHeight(root.leftChild), getTreeHeight(root.rightChild)) + 1;
        }
    }
}
