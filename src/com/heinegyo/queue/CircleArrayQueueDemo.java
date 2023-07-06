package com.heinegyo.queue;

import java.util.Scanner;

/**
 * 在原有的Queue進行改進
 * 1. front初始值 指向Queue的第一個元素，也就是說arr[front]就是Queue的第一個元素
 * 2. rear初始值 指向Queue的最後一個元素後一位置，流出一個空間作約定
 * 3. Queue空的條件為 rear == front
 * 4. Queue滿的條件為 (rear+1)%maxSize = front
 * 5. Queue的有效元素數量為 (rear+maxSize - front ) % maxSize //ex. rear = 1,front =0
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
                        System.out.printf("取出的元素是%d\n", res);
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.peek();
                        System.out.printf("queue head : %d\n", res);
                    } catch (Exception e) {
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

class CircleArrayQueue implements Queue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];

    }

    @Override
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void enqueue(int n) {
        if (isFull()) {
            System.out.println("Queue is full !");
            return;
        }
        //直接將元素加入
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return 0;
        }
        //這裡需要分析出front是指向Queue的第一個元素
        //1. 先把front 對應的值保存到臨時變數
        //2. 先把front 後移
        //3. return 保存的變數
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    @Override
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        // 從front 到 rear 需要走訪幾個元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }

    }


    //求出Queue有效數據的個數
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    @Override
    public int peek() {
        return arr[front];
    }
}