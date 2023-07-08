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
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();
    }
}

//定義SingLinkedList 管理英雄角色
class SingleLinkedList {
    //先初始化head，head不要動，不存放具體資料
    private HereNode head = new HereNode(0,"","");

    //新增node到單向鏈錶
    //不考慮編號順序時
    //1.找到目前linkedList最後的節點
    //2.將最後這個節點的next 指向 新的節點
    public void add(HereNode hereNode){
        //因為head節點不能動，因此我們需要一個輔助遍歷temp
        HereNode temp = head;
        //走訪鏈錶，找到最後
        while (true){
            if (temp.next == null){
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
    public void list(){
        System.out.println("test.....");
        //判斷linkedList是否為空
        if(head.next == null){
            System.out.println("linkedList is empty!");
        }
        //因為head不能動，因此我們需要一個輔助的變數來走訪
        HereNode temp = head.next;
        while (true){
            //判斷是否到linkedList最後
            if (temp == null){
                break;
            }
            //輸出node
            System.out.println(temp);
            //將next後移
            temp = temp.next;
        }
    }

}

class HereNode{
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