package com.heinegyo.hashtable;

import java.util.Scanner;

public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: add employee");
            System.out.println("list: show employee");
            System.out.println("find: search employee");
            System.out.println("exit: exit");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("enter id");
                    int id = scanner.nextInt();
                    System.out.println("enter name");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}


class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    /**
     * 表示有多少條 linked list
     */
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //有坑？
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    /**
     * 遍歷所有linked list
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.println("find Emp By Id : " + emp + " in no." + (empLinkedListNo + 1) + "LinkedList");
        } else {
            System.out.println("Not found !");
        }

    }

    //編寫散列函數，使用簡單的取餘
    public int hashFun(int id) {
        return id % size;
    }

}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

class EmpLinkedList {

    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }

        curEmp.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("linkedList no." + (no + 1) + " is empty!");
            return;
        }
        System.out.print("linked list No." + (no + 1));
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s \t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("linkedList is empty !");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}