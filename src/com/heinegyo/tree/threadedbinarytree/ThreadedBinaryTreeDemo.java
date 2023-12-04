package com.heinegyo.tree.threadedbinarytree;

/**
 * 引線二元樹
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //測試線索化
        ThreadedTree threadedTree = new ThreadedTree();
        threadedTree.setRoot(root);
        threadedTree.threadedNodes();

        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        System.out.println(".............");
        threadedTree.threadedList();
    }
}


class ThreadedTree {

    private HeroNode root;

    private HeroNode pre = null;


    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes(HeroNode node) {

        //如果node == null ，不能線索化
        if (node == null) {
            return;
        }

        // (一)先線索化 左子樹
        threadedNodes(node.getLeft());

        // (二)線索化curNode
        // 2-1處理前驅節點
        if (node.getLeft() == null) {
            //指向前驅節點
            node.setLeft(pre);
            //修改當前節點的左指針的類型，指向前驅節點
            node.setLeftType(1);
        }
        // 2-2處理後繼節點
        if (pre != null && pre.getRight() == null) {
            //讓前驅節點的右指針指向curNode
            pre.setRight(node);
            //修改前驅節點的右指針類型
            pre.setRightType(1);
        }

        ///!!!! 每次處理一個節點後，讓curNode是nextNode的pre
        pre = node;

        //(三)再線索化 右子樹
        threadedNodes(node.getRight());

    }

    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            //循環找到leftType == 1的節點，第一個找到就是8節點
            //後面的隨著走訪而變化，因為當leftType==1時，說明該節點是按照線索化
            //處理後的有效節點
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);
            //如果目前的節點右指針指向的的是後繼節點，就一直輸出
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                //遞迴刪除
                root.delNode(no);
            }
        } else {
            System.out.println("empty tree");
        }
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

    /**
     * 說明
     * 1. 如果leftType == 0 表示指向的是左子樹，如果1則表示指向前驅節點
     * 2. 如果rightType == 0 表示指向的是右子樹，如果1則表示指向後繼節點
     */
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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

    public void delNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }

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
