
public class MyArray {
    private int[] arr;
    private int size;

    MyArray(int maxSize) {
        this.size = 0;
        this.arr = new int[maxSize];
    }

    void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    void insert(int value) {
        if (this.size >= this.arr.length - 1) {
            int[] tempArr = this.arr;
            this.arr = new int[this.size * 2];
            System.arraycopy(tempArr, 0, this.arr, 0, size);
        }
//        int i;
//        for (i = 0; i < this.size; i++) {
//            if (this.arr[i] > value) break;
//        }
//        System.arraycopy(this.arr, i, this.arr, i + 1, this.size - i);
//        arr[i] = value;
        arr[this.size] = value;
        this.size++;
    }

    boolean delete(int value) {
        int pos = getPosition(value);
        if (pos != -1) {
            System.arraycopy(this.arr, pos + 1, this.arr, pos, this.size - pos - 1);
            this.size--;
            return true;
        }
        return false;
    }

    int getPosition(int value) {
        for (int i = 0; i < this.size; i++)
            if (this.arr[i] == value)
                return i;
        return -1;
    }

    boolean isInArray(int value) {
        for (int i = 0; i < this.size; i++)
            if (this.arr[i] == value)
                return true;
        return false;
    }

    boolean hasValue(int value) {
        int low = 0;
        int high = this.size - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (value == this.arr[mid]) {
                return true;
            } else {
                if (value < this.arr[mid])
                    high = mid;
                else
                    low = mid + 1;
            }
        }
        return false;
    }

    void sortBubble() {  // O(n * k)
        for (int out = this.size - 1; out > 1; out--)
            for (int in = 0; in < out; in++)
                if (this.arr[in] > this.arr[in + 1])
                    itemSwap(in, in + 1);
    }

    void sortSelect() {
        int mark;
        for (int out = 0; out < this.size - 1; out++) {
            mark = out;
            for (int in = out + 1; in < this.size; in++)
                if (this.arr[in] < this.arr[mark])
                    mark = in;

            itemSwap(out, mark);
        }
    }

    void sortInsert() {
        int in;
        for (int out = 1; out < this.size; out++) {
            int temp = this.arr[out];
            in = out;
            while (in > 0 && this.arr[in - 1] >= temp) {
                this.arr[in] = this.arr[in - 1];
                in--;
            }
            this.arr[in] = temp;
        }
    }

    private void itemSwap(int from, int to) {
        int tmp = this.arr[from];
        this.arr[from] = this.arr[to];
        this.arr[to] = tmp;
    }

}
