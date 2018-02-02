package teacherVersion;

import lesson.Queue;

public class Deque extends Queue {
    public Deque(int size) {
        super(size);
    }

    public void enqueueTail(int i) {
        super.insert(i);
    }

    public void enqueueHead(int i) {
        if (isFull())
            throw new RuntimeException("lesson.Queue is full");

        if (getHead() == 0)
            setTail(getSize());

        getQueue()[getHead() - 1] = i;
        setHead(getHead() - 1);
        setItems(getItems() + 1);
    }

    public int dequeueHead() {
        return super.remove();
    }

    public int dequeueTail() {
        if (isEmpty())
            throw new RuntimeException("lesson.Queue is empty");

        int temp = getQueue()[getTail()];
        setTail(getTail() + 1);
        if (getTail() == getSize())
            setTail(0);

        setItems(getItems() - 1);
        return temp;
    }


}
