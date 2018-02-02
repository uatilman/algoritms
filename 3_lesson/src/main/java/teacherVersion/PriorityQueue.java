package java_asd.three;

public class PriorityQueue extends Queue {
    public PriorityQueue(int size) {
        super(size);
    }

    @Override
    public int remove() {
        int temp = getQueue()[0];
        System.arraycopy(getQueue(), 1, getQueue(), 0, getSize());
        setSize(getSize() - 1);
        return temp;

    }

    @Override
    public void insert(int newVal) {
        if (isFull())
            throw new RuntimeException("lesson.Queue is full");

        int i;
        for (i = 0; i < getSize(); i++)
            if (getQueue()[i] > newVal) break;

        System.arraycopy(getQueue(), i, getQueue(), i + 1, getSize() - i);
        getQueue()[i] = newVal;
        setSize(getSize() + 1);

    }
}