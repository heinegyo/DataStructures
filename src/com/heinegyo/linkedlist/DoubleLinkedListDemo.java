package com.heinegyo.linkedlist;

/**
 * 1. 遍歷雙向鏈表與單向鏈表一樣，只是可以向前，也可以向後找
 * 2. add(預設新增到雙向鏈表的最後)
 *      2-1. 先找到雙向鏈表最後的節點
 *      2-2. temp.next = newHeroNode
 *      2-3. newHeroNode.pre = temp
 * 3. 修改與單向鏈表一致
 * 4. 刪除
 *      4-1. 因為是雙向鏈表，因此，可以實現自我刪除某個節點
 *      4-2. 直接找到要刪除的這個節點，比如temp
 *      4-3. temp.pre.next = temp.next
 *      4-4. temp.next.pre=temp.pre
 *
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("雙向鏈表測試");
        HereNode2 hero1 = new HereNode2(1, "宋江", "及時雨");
        HereNode2 hero2 = new HereNode2(2, "盧俊義", "玉麒麟");
        HereNode2 hero3 = new HereNode2(3, "無用", "智多星");
        HereNode2 hero4 = new HereNode2(4, "林沖", "豹子頭");
        //建立一個雙向鍊錶
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        //update
        HereNode2 newHeroNode = new HereNode2(4,"諸葛村夫","你tm");
        System.out.println("DoubleLinkedList update");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        //del
        System.out.println("DoubleLinkedList delete");
        doubleLinkedList.del(4);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList{
    private HereNode2 head = new HereNode2(0, "", "");

    public HereNode2 getHead(){
        return head;
    }

    //走訪雙向鏈表
    //顯示linkedList
    public void list() {
        //判斷linkedList是否為空
        if (head.next == null) {
            System.out.println("linkedList is empty!");
        }
        //因為head不能動，因此我們需要一個輔助的變數來走訪
        HereNode2 temp = head.next;
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


    public void add(HereNode2 hereNode) {
        //因為head節點不能動，因此我們需要一個輔助遍歷temp
        HereNode2 temp = head;
        //走訪鏈錶，找到最後
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果沒有找到最後，將temp指向後一個
            temp = temp.next;
        }
        //當退出循環後，temp就指向了linkedList的最後
        //形成雙向鏈表
        temp.next = hereNode;
        hereNode.pre = temp;
    }

    //邏輯與單向鏈表一致
    public void update(HereNode2 newHereNode) {
        if (head.next == null) {
            System.out.println("鏈表為空");
            return;
        }
        //找到需要修改的節點，根據no編號
        //定義一個輔助變數
        HereNode2 temp = head.next;
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
     * 從雙向鏈表中刪除一個節點
     * 1. 對於雙向鏈表，可以直接找到要刪除的節點
     * 2. 找到後，自我刪除即可
     * @param no
     */
    public void del(int no) {
        if (head.next == null) {
            System.out.println("linkedList is empty");
        }

        HereNode2 temp = head.next; //輔助指針
        //標示是否走到待刪除節點
        boolean flag = false;
        while (true) {
            if (temp == null){
                break;
            }
            if (temp.no == no) {
                //找到待刪除節點的前一個節點temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // temp.next = temp.next.next; -> 單向鍊錶刪除邏輯
            temp.pre.next = temp.next;
            //!!!
            //如果是最後一個節點，就不需要執行以下程式碼，否成會出現NullPinterException
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("linkedList中沒有該節點 %d ", no);
        }
    }
}

class HereNode2 {
    public int no;
    public String name;
    public String nickname;
    public HereNode2 next;  //指向下一個節點
    public HereNode2 pre;   //指向上一個節點

    public HereNode2(int no, String name, String nickname) {
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