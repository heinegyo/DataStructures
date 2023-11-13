package com.heinegyo.tree;


public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "Iron Man");
        HeroNode node2 = new HeroNode(2, "Captain America");
        HeroNode node3 = new HeroNode(3, "Star-Lord");
        HeroNode node4 = new HeroNode(4, "Doctor Strange");
        HeroNode node5 = new HeroNode(5, "Ant-Man");

        root.setLeft(node2);System.out.println();
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

        //binaryTree.preOrder();
        // System.out.println();
        //binaryTree.infixOrder();
        // System.out.println();
        binaryTree.postOrder();
    }


}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("BinaryTree is empty");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("BinaryTree is empty");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("BinaryTree is empty");
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }

    //前序遍歷
    public void preOrder() {
        //先輸出父節點
        System.out.println(this);
        //遞迴向左
        if (this.left != null) {
            this.left.preOrder();
        }
        //遞迴向右
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        //遞迴向左
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        //遞迴向右
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}
