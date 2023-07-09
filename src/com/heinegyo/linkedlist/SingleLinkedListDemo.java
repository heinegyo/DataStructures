package com.heinegyo.linkedlist;

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
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();

        //測試修改節點
        HereNode newHeroNode = new HereNode(2, "小盧", "~~");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();
    }
}

//定義SingLinkedList 管理英雄角色
class SingleLinkedList {
    //先初始化head，head不要動，不存放具體資料
    private HereNode head = new HereNode(0, "", "");

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
        System.out.println("test.....");
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