package lesson;

public class Queue {

    private int size;
    private int[] queue;
    private int head;
    private int tail;
    private int items;

    public Queue(int size) {
        this.size = size;
        this.queue = new int[size];
        head = 0;
        tail = -1;
        items = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getQueue() {
        return queue;
    }

    public void setQueue(int[] queue) {
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

    public void insert(int i) {
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
    public int remove() {
        if (isEmpty())
            throw new RuntimeException("lesson.Queue is empty");

        int temp = queue[head++];
        if (head == size)
            head = 0;

        items--;
        return temp;
    }

    public int peek() {
        return queue[head];
    }

}
