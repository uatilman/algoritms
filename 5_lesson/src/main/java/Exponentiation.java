public class Exponentiation {

    public static void main(String[] args) {

        //**** Написать программу по возведению числа в степень с помощью рекурсии.
        for (int i = 1; i < 9; i++) {
            System.out.println("exp " + i + " " + getExponent(2, i));

        }
    }

    public static int getExponent(int base, int exponent) {
        return calcExponent(base, base, exponent);
    }

    private static int calcExponent(int base, int currentValue, int exponent) {
        if (exponent == 1) return currentValue;
        return calcExponent(base, currentValue * base, exponent - 1);
    }

}
