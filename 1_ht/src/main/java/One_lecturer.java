public class One_lecturer {

    private static int power(int base, int sign) {
        int result = 1;
        for (int i = 0; i < sign; i++)
            result *= base;

        return result;
    }

    private static int arrMin(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < result)
                result = arr[i];

        return result;
    }

    private static float arrMean(int[] arr) {
        float result = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++)
            result += arr[i];

        return result / size;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 10));
        int[] array = {5, 1, 4, 2, 0, 3};
        System.out.println(arrMin(array));
        System.out.println(arrMean(array));
    }
}
