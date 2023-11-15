package com.heinegyo.tree;


public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "Iron Man");
        HeroNode node2 = new HeroNode(2, "Captain America");
        HeroNode node3 = new HeroNode(3, "Star-Lord");
        HeroNode node4 = new HeroNode(4, "Doctor Strange");
        HeroNode node5 = new HeroNode(5, "Ant-Man");

        root.setLeft(node2);
        System.out.println();
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

        //System.out.println("preOrder traversal");
        //binaryTree.preOrder();
        //System.out.println("infixOrder traversal");
        //binaryTree.infixOrder();
        //System.out.println("postOrder traversal");
        //binaryTree.postOrder();

        System.out.println("preOrderSearch");
        System.out.println(binaryTree.preOrderSearch(5));

        System.out.println("infixOrderSearch");
        System.out.println(binaryTree.infixOrderSearch(6));

        System.out.println("postOrderSearch");
        System.out.println(binaryTree.postOrderSearch(5));
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

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
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

    public HeroNode preOrderSearch(int no) {
        System.out.println("preOrderSearch...");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("infixOrderSearch....");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("postOrderSearch.....");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
