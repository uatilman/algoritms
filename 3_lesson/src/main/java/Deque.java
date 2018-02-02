/**
* **** Создать класс для реализации дека
* */
public class Deque {
    private int size;
    private int[] queue;
    private int head;
    private int tail;
    private int items;

    public Deque(int size) {
        if (size <= 1)
            throw new RuntimeException("Too small for stack " + size);
        this.size = size;
        this.queue = new int[size];
        this.head = -1;
        this.tail = -1;
        this.items = 0;
    }

    public static void main(String[] args) {
        Deque deque = new Deque(3);
        int ITER = 6;
        for (int i = 0; i < ITER; i++) {
            if ((i % 2) == 0) {
                deque.putFirst(100 + i);
            } else {
                deque.putLast(100 - i);
            }
        }
        deque.print();

        System.out.println("get Last " + deque.getLast() + " ");
        System.out.println("get Last " + deque.getLast() + " ");


        for (int i = 0; i < (ITER - 1) / 2; i++) {
            System.out.println("get First " + deque.getFirst() + " ");
            deque.print();
            System.out.println("get Last " + deque.getLast() + " ");
            deque.print();
        }

    }

    public void print() {
        System.out.print("Deque: ");
        for (int i = tail; i <= head; i++) {
            System.out.print(queue[i] + "*");
        }
        System.out.println();
    }

    public void putLast(int i) {
        if (isFull()) {
            this.size *= 2;
            int[] newQueue = new int[this.size];
            System.arraycopy(this.queue, 0, newQueue, 0, this.queue.length);
            this.queue = newQueue;
        }
        this.queue[++this.head] = i;
        items++;
    }

    public void putFirst(int i) {
        if (isFull() || tail <= 0) {
            this.size *= 2;
            int[] newQueue = new int[this.size];
            System.arraycopy(this.queue, 0, newQueue, 1, this.queue.length);
            this.queue = newQueue;
            this.head++;
            this.tail = 0;
            this.queue[tail] = i;
        } else {
            this.queue[--tail] = i;
        }

        items++;
    }

    public int getFirst() {
        if (isEmpty()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Deque is empty");
        }
        items--;
        return this.queue[tail++];
    }


    public int getLast() {
        if (isEmpty()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Deque is empty");
        }
        int temp = this.queue[this.head--];
        items--;
        return temp;
    }

    public boolean isFull() {
        return items == size;
    }

    public boolean isEmpty() {
        return items <= 0;
    }
}