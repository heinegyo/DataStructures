package com.heinegyo.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        //接收使用者輸入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //輸出一個菜單
        while (loop) {
            System.out.println("s(show): showQueue");
            System.out.println("e(exit): exit");
            System.out.println("a(add): add");
            System.out.println("g(get): get");
            System.out.println("h(head): head");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("輸出一個數");
                    int value = scanner.nextInt();
                    queue.enqueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.dequeue();
                        System.out.printf("取出的元素是%d\n" ,res);
                    }catch (Exception e){
                        //TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.peek();
                        System.out.printf("queue head : %d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }

        System.out.println("end ....");
    }
}

//使用陣列模擬Queue
class ArrayQueue implements Queue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向queue起始位置，分析出front是指向Queue第一個元素的前一個位置
        rear = -1;//指向queue尾部，是指向Queue最後一個元素的個位置(包含)
    }

    //判斷Queue是否滿
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判斷Queue是否為空
    public boolean isEmpty() {
        return rear == front;
    }

    public void enqueue(int n) {
        if (isFull()) {
            throw new RuntimeException("Queue is full !");
        }
        rear++;
        arr[rear] = n;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        return arr[front + 1];
    }
}