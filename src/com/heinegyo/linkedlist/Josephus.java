package com.heinegyo.linkedlist;

/**
 * Josephus問題
 * 設定編號為1，2...n，的n個人圍坐一圈，約定編號為k（1<=k<=n）的人從1開始報數，
 * 數到m的那個人出列，它的下一個又從1開始報數，數到m個的那個人又出列，
 * 以次類推，直到所有人出列為止，由此產生一個出列序號的序列。
 */
public class Josephus {

    public static void main(String[] args) {
        //測試一把看看構建環形鏈表，和遍歷是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //測試一把小孩是否出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList {
    //建立一個first節點
    private Boy first = new Boy(-1);

    /**
     * 建構一個單向的CircleSingleLinkedList
     * 1. 讓first指向該節點，並形成環形
     * 2. 後面當我們每建立一個新的節點，就把該節點，加入到已有的鏈表中即可
     *
     * @param nums
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 值不正確");
            return;
        }

        //輔助變數，幫助構建CircleSingleLinkedList
        Boy curBoy = null;
        //使用for來建立環形鏈表
        for (int i = 1; i <= nums; i++) {
            //根據編號建立Boy節點
            Boy boy = new Boy(i);
            //如果是第一個Boy
            if (i == 1) {
                first = boy;
                first.setNext(first);//構成Circle
                curBoy = first;//讓curBoy指向第一個節點
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    /**
     * 遍歷CircleSingleLinkedList
     * 1. 先讓一個輔助指針(變數)curBoy，指向first節點
     * 2. while循環遍歷環形鏈表即可curBoy.next == first結束
     */
    public void showBoy() {
        //判斷鏈表為空
        if (first == null) {
            System.out.println("CircleSingleLinkedList is empty!!");
            return;
        }
        //因為first不能動，因此我們仍然使用一個輔助指針完成遍歷
        Boy curBoy = first;
        while (true) {
            System.out.printf("Boy no : %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根據使用者的輸入，計算節點出圈的順序
     * n=5，即有5個人
     * k=1,從第一個人開始報數
     * m=2,數2下
     * 1. 需要建立一個輔助指針(變數)helper，事先應該指向環形鏈表的最後一個節點、
     * 2. 節點報數前，先讓first和helper移動k-1次
     * 3. 當元素報數，讓first和helper指針同時移動m-1次
     * 4. 這時就可以將first指向的節點出圈
     * first = first.next
     * helper.next = first
     * 原來first指向的節點就沒有任何引用，就會被回收
     *
     * @param startNo  表示從第幾個節點開始數數(k)
     * @param countNum 表示數幾下(m)
     * @param nums     表示最初有多少小孩在圈中(n)
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("參數輸入有誤！請重新輸入");
            return;
        }
        //建立要給輔助指針，幫助完成節點出圈
        Boy helper = first;
        // 需要建立一個輔助指針(變數)helper，事先應該指向環形鏈表的最後一個節點
        while (true) {
            if (helper.getNext() == first) { //說明helper指向最後的節點
                break;
            }
            helper = helper.getNext();
        }
        //節點報數前，先讓first和helper 移動k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //當節點報數時，讓first和helper指針同時移動m-1次，然後出圈
        //這是循環操作，直到圈中只有一個節點
        while (true) {
            if (helper == first) { //說明圈中只剩一個節點
                break;
            }
            //讓first和helper指針同時 移動countNum-1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //這時first指向的節點，就是要出圈的節點
            System.out.printf("節點 %d 出圈 \n", first.getNo());
            //這時將first指向的小孩節點出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最後留在圈中的節點為%d \n", helper.getNo());
    }
}


//建立一個Boy類，表示一個節點
class Boy {
    //編號
    private int no;
    //指向下一個節點,預設null
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
