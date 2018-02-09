package ht;

import java.lang.reflect.Array;

public class Queue <E> {

    private int size;
    private Object[] queue;
    private int head;
    private int tail;
    private int items;

    public Queue(int size) {
        this.size = size;
        this.queue = new Object[size];
        head = 0;
        tail = -1;
        items = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    public E[] getQueue() {
//        return (E[])queue;
//    }

    public void setQueue(E[] queue) {
        this.queue = queue;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == size;
    }

    public int getSize() {
        return items;
    }

    public void add(E i) {
        if (isFull())
            throw new RuntimeException("lesson.Queue is full");

        if (tail == size - 1)
            tail = -1;

        queue[++tail] = i;
        items++;

    }
//
//*** Домашнее задание
//**** Создать программу, которая переворачивает вводимые строки
//**** Создать класс для реализации дека
//**** Реализовать класс с реализацией приоритетной очереди
    public E remove() {
        if (isEmpty())
            throw new RuntimeException("lesson.Queue is empty");

        Object temp = queue[head++];
        if (head == size)
            head = 0;

        items--;
        return (E)temp;
    }

    public E peek() {
        return (E)queue[head];
    }

}
