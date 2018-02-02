/**
 * **** Реализовать класс с реализацией приоритетной очереди
 */
public class PriorityQueue {
    //    private int size;
    private int[] queue;
    private int head;
    private int tail;

    public PriorityQueue(int size) {
        if (size <= 1)
            throw new RuntimeException("Too small for stack " + size);
//        this.size = size;
        this.queue = new int[size];
        this.tail = 0;
        this.head = 0;
    }

    public void put(int value) {
        int i;
        for (i = head; i < tail; i++)
            if (queue[i] > value)
                break;
        if (tail + 1 == queue.length) {
            addSize();
        }
        System.arraycopy(this.queue, i, this.queue, i + 1, tail++ - i);

        queue[i] = value;
    }

    //TODO возможно оптимизировать и сдигать голову при удалении всегда на 0, тогда - 1 поле и -масса пустых полей, но необходимо простоянное копирование в 0.
    //TODO Идеально бы было сместить указатель начала масива на элемент справа, но это java
    public int pop() {
        if (isEmpty()) {
            try {
                Thread.sleep(100);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("lesson.Queue is empty");
        }
        return queue[head++];
    }

    private void addSize() {
//        size = size * 2;
        int[] newQueue = new int[this.queue.length * 2];
        System.arraycopy(this.queue, 0, newQueue, 0, this.queue.length);
        this.queue = newQueue;
    }

    public void print() {
        System.out.print("\nlesson.Queue: ");
        for (int i = head; i < tail; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.printf("head %d, tail %d, queue.length %d \n", head, tail, queue.length);
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public int getLength() {
        return tail - head;
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue(3);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 7; i++) {
                System.out.print(i + "..");
                pq.put(i + 100 * (j + 1));
            }
            pq.print();

            for (int i = 0; i < 3; i++) {
                System.out.print(pq.pop() + " ");
            }
            System.out.println();
            pq.print();
            System.out.println("+++++++++++");

        }

        for (int i = 0; i < 13; i++) {
            System.out.print(pq.pop() + " ");
        }


    }
}
