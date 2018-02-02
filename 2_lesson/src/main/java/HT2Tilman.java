import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class HT2Tilman {
    private int size;
    private int arr[];
    private Path path;

    public static void main(String[] args) throws Exception {

        int maxSize = 1000000;
        HT2Tilman obj = new HT2Tilman(maxSize, Paths.get("res.txt"));
        int[] arr1 = new int[maxSize];
        int[] arrTemp = new int[maxSize];

        //Создание массива для хранения исходного, для проведения применения сортировок к одинаковым массивам
        System.arraycopy(obj.getArr(), 0, arrTemp, 0, obj.getSize());


        Method m[] = HT2Tilman.class.getDeclaredMethods();
        int countUseMethods = 0;

        for (int i = 0; i < m.length; i++) {
            //Тестирование сортировок
            if (m[i].isAnnotationPresent(Sorts.class)) {
                try {
                    System.arraycopy(arrTemp, 0, obj.getArr(), 0, arrTemp.length);
                    long t = System.nanoTime();
                    m[i].invoke(obj, null);
                    long dt = System.nanoTime() - t;

                    String compareResult = (countUseMethods++ != 0) ? ("compare result: " + obj.testCompare(arr1)) : "compare result: null - first sort";
                    obj.myLog(m[i].getName() + " ",
                            compareResult,
                            arr1.length,
                            dt);
                    System.arraycopy(obj.getArr(), 0, arr1, 0, obj.getSize());

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    Files.write(obj.getPath(), ("Method " + m[i].getName() + " " + e.getMessage()).getBytes(), StandardOpenOption.APPEND);
                }
                //Тестирование поиска
            } else if (m[i].isAnnotationPresent(Find.class)) {
                try {
                    System.arraycopy(arrTemp, 0, obj.getArr(), 0, arrTemp.length);
                    long t = System.nanoTime();
                    m[i].invoke(obj, 25700);
                    long dt = System.nanoTime() - t;

                    obj.myLog(m[i].getName() + " ",
                            "",
                            arr1.length,
                            dt);
                    System.arraycopy(obj.getArr(), 0, arr1, 0, obj.getSize());

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    Files.write(obj.getPath(), ("Method " + m[i].getName() + " " + e.getMessage()).getBytes(), StandardOpenOption.APPEND);
                }
            }
        }
    }

    public HT2Tilman(int maxSize, Path path) {
        this.size = 0;
        this.arr = new int[maxSize];

        createArrDate();
        this.path = path;
    }

    public HT2Tilman(int maxSize) {
        this.size = 0;
        this.arr = new int[maxSize];
    }

    private void createArrDate() {
        for (int anArr : arr) add((int) (1000 * Math.random() + 1));
    }

    public void add(int value) {
        if (size >= arr.length - 1) {
            int temp[] = arr;
            arr = new int[size * 2];
            System.arraycopy(temp, 0, arr, 0, size);
        }
        arr[size] = value;
        size++;
    }

    public boolean delete(int value) {
        int pos = getIndex(value);
        if (pos >= 0) {
            System.arraycopy(arr, pos + 1, arr, pos, size - pos - 1);
            size--;
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public boolean isExist(int value) {
        return getIndex(value) >= 0;
    }

    @Find
    public int getIndex(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    @Find
    public int getIndexDoubleFind(int value) {
        int low = 0;
        int high = size - 1;
        int mid;
        while (low <= high) { //На уроке тут было <, сответственно верхнее значение не попадало в поиск
            mid = (high + low) / 2;
            if (value == arr[mid]) {
                return mid;
            } else {
                if (value < arr[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    //region Sorts
    @Sorts
    public void mySortInsert() { // O(n * k) ~O(n^2) //сортировка вставками
        int in;
        for (int out = 1; out < size; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
    }

    @Sorts
    public void mySortSelect() { //// O(n * k) - сортировка выбором
        int mark;
        for (int out = 0; out < size - 1; out++) {
            mark = out;
            for (int in = out + 1; in < size; in++)
                if (arr[in] < arr[mark])
                    mark = in;
            change(arr, out, mark);
        }
    }

    @Sorts
    public void myBubble() {
        for (int out = size - 1; out > 1; out--)
            for (int in = 0; in < out; in++)
                if (arr[in] > arr[in + 1])
                    change(arr, in, in + 1);
    }

    @Sorts
    public void javaSort() {
        int another[] = new int[size];
        System.arraycopy(arr, 0, another, 0, size);
        Arrays.sort(another);
        System.arraycopy(another, 0, arr, 0, size);
    }

    public boolean testCompare(int another[]) {
        int i;
        for (i = 0; i < size - 1; i++) {
            if (this.arr[i] != another[i]) return false;
        }
        return true;
    }

    //endregion
    public void myLog(String methodName, String otherInformation, int length, long dt) throws IOException {
        System.out.println(methodName + ": length = " + length + ", time (ns/s) " + String.valueOf(dt) + "/" + String.valueOf(dt / 1000000000) + ". " + otherInformation);
        Files.write(path, (methodName + ": length = " + length + ", time (ns/s) " + String.valueOf(dt) + "/" + String.valueOf(dt / 1000000000) + ". " + otherInformation + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    public void change(int[] arr, int from, int to) {
        if (from == to) return;
        arr[from] = arr[from] ^ arr[to];
        arr[to] = arr[from] ^ arr[to];
        arr[from] = arr[from] ^ arr[to];
    }

    //region getters
    public int getSize() {
        return size;
    }

    public Path getPath() {
        return path;
    }

    public int[] getArr() {
        return arr;
    }

    //endregion
}
