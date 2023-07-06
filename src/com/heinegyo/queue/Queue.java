package com.heinegyo.queue;

interface Queue {

    boolean isFull();

    boolean isEmpty();

    void enqueue(int n);

    int dequeue();

    void showQueue();

    int peek();
}
