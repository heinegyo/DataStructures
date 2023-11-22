package com.heinegyo.tree;

public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println();
        arrBinaryTree.infixOrder();
        System.out.println();
        arrBinaryTree.postOrder();

    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is empty!");
        }

        System.out.println(arr[index]);

        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder() {
        infixOrder(0);
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is empty!");
        }

        if ((index * 2 + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }

        System.out.println(arr[index]);

        if ((index * 2 + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder() {
        postOrder(0);
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is empty!");
        }

        if ((index * 2 + 1) < arr.length) {
            postOrder(2 * index + 1);
        }

        if ((index * 2 + 2) < arr.length) {
            postOrder(2 * index + 2);
        }

        System.out.println(arr[index]);
    }

}
