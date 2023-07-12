package com.heinegyo.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // System.out.println("hello");
        //test
        HereNode hero1 = new HereNode(1, "宋江", "及時雨");
        HereNode hero2 = new HereNode(2, "盧俊義", "玉麒麟");
        HereNode hero3 = new HereNode(3, "無用", "智多星");
        HereNode hero4 = new HereNode(4, "林沖", "豹子頭");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        // singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        //singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();

        reverse(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("test reversePrint ");
        reversePrint(singleLinkedList.getHead());

        //測試修改節點
        // HereNode newHeroNode = new HereNode(2, "小盧", "~~");
        // singleLinkedList.update(newHeroNode);
        // singleLinkedList.list();

        //刪除一個節點
        // singleLinkedList.del(1);
        // singleLinkedList.del(1);
        // singleLinkedList.list();
        //
        // System.out.println(getLength(singleLinkedList.getHead()));
        //
        // HereNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
        // System.out.println(res);


    }

    /**
     * 查詢singleLinkedList 的倒數第k個節點
     * 1. 寫一個方法，接收head節點，同時接收一個index
     * 2. index 表示是倒數第index個節點
     * 3. 先把singleLinkedList 走訪一次 確認個數
     * 4. 得到size後，從鏈表的第一個開始走訪(size-index)
     *
     * @param head
     * @return
     */
    public static HereNode findLastIndexNode(HereNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HereNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static int getLength(HereNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HereNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }


    public static void reverse(HereNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定義一個輔助的指針，幫助我們走訪原來的鏈表
        HereNode cur = head.next;
        //指向cur的下一個節點
        HereNode next = null;
        HereNode reverseHead = new HereNode(0, "", "");
        while (cur != null){
            //先暫時保存cur的下一個節點，後面會用到
            next = cur.next;
            //將cur的的下一個節點指向新的鏈表的最前端
            cur.next = reverseHead.next;
            //將cur連接到新的鏈表上
            reverseHead.next = cur;
            //讓cur後移
            cur = next;
        }
        //將head.next 指向 reverseHead.next 實現單鏈表的反轉
        head.next = reverseHead.next;
    }

    /**
     * 從尾部print SingleLinkedList - 逆序print
     * 1. 先將SingleLinkedList 進行反轉，然後在遍歷即可，但是會破壞原有結構
     * 2. 使用stack，利用先進後出的特點
     */
    public static void reversePrint(HereNode head){
        if(head.next == null){
            return;
        }
        Stack<HereNode> stack = new Stack<>();
        HereNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}

//定義SingLinkedList 管理英雄角色
class SingleLinkedList {
    //先初始化head，head不要動，不存放具體資料
    private HereNode head = new HereNode(0, "", "");

    public HereNode getHead() {
        return head;
    }

    //新增node到單向鏈錶
    //不考慮編號順序時
    //1.找到目前linkedList最後的節點
    //2.將最後這個節點的next 指向 新的節點
    public void add(HereNode hereNode) {
        //因為head節點不能動，因此我們需要一個輔助遍歷temp
        HereNode temp = head;
        //走訪鏈錶，找到最後
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果沒有找到最後，將temp指向後一個
            temp = temp.next;
        }
        //當退出循環後，temp就指向了linkedList的最後
        //將最後這個節點的next指向新的節點
        temp.next = hereNode;
    }

    //顯示linkedList
    public void list() {
        //判斷linkedList是否為空
        if (head.next == null) {
            System.out.println("linkedList is empty!");
        }
        //因為head不能動，因此我們需要一個輔助的變數來走訪
        HereNode temp = head.next;
        while (true) {
            //判斷是否到linkedList最後
            if (temp == null) {
                break;
            }
            //輸出node
            System.out.println(temp);
            //將next後移
            temp = temp.next;
        }
    }

    //第二種方式在新增英雄時，根據排名將英雄插入到指定位置
    //(如果有這個排名，則新增失敗，並給出提示)
    public void addByOrder(HereNode hereNode) {
        //因為head不能動，因此仍然通過一個輔助指針(變數)來幫助找到新增的位置
        //因為單鏈表，我們找的temp是位於位置的前一個節點，否則插入
        HereNode temp = head;
        //標示新增的編號是否存在，預設為false
        boolean flag = false;
        while (true) {
            //說明temp已經在鏈表的最後
            if (temp.next == null) {
                break;
            }

            if (temp.next.no > hereNode.no) { //位置找到，就在temp後面insert
                break;
            } else if (temp.next.no == hereNode.no) { //說明希望新增的heroNode的編號已經存在
                flag = true; //說明編號存在
                break;
            }
            temp = temp.next; //後移，相當於走訪鏈表
        }
        //判斷flag的值
        if (flag) { //無法新增，說明編號存在
            System.out.printf("準備新增的英雄編號 %d 已經存在了，不能加入\n", hereNode.no);
        } else {
            //插入到鏈表中,temp後面
            hereNode.next = temp.next;
            temp.next = hereNode;

        }
    }

    //修改節點,根據編號no來修改 ，即no編號不能改
    //說明
    //1.根據newHeroNode 的 來修改
    public void update(HereNode newHereNode) {
        if (head.next == null) {
            System.out.println("鏈表為空");
            return;
        }
        //找到需要修改的節點，根據no編號
        //定義一個輔助變數
        HereNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHereNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根據flag 判斷是否找到要修改的節點
        if (flag) {
            temp.name = newHereNode.name;
            temp.nickname = newHereNode.nickname;
        } else {
            System.out.printf("沒有找到編號%d的節點，不能修改\n", newHereNode.no);
        }
    }

    /**
     * 從singleLinkedList中刪除一個節點
     * 1.先找到需要這個刪除的節點的前一個節點temp
     * 2.temp.next= temp.next.next
     * 3.被刪除的節點，不會有其他引用指向，會被GC
     */
    public void del(int no) {
        HereNode temp = head;
        //標示是否走到待刪除節點
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //找到待刪除節點的前一個節點temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("linkedList中沒有該節點 %d ", no);
        }
    }
}

class HereNode {
    public int no;
    public String name;
    public String nickname;
    public HereNode next;

    public HereNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HereNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}