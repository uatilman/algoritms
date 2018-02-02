
//Написать простейшие алгоритмы
// - Возведение в степень
// - Поиск минимальных элементов в массивее
// - Нахождения среднего арифметического массива

// Подсчитать сложность описанных алгоритмов

// Какие правила подсчета применялись, комментарии оставить в коде

public class HT1Tilman {
    public static void main(String[] args) {
        int base = 5;
        for (int i = -3; i < 4; i++) {
            System.out.printf("%d^%d = %d *** %f \n", base, i, exponentiation(base, i), Math.pow(base, i));
        }
        System.out.println(arrayMinimum(new int[]{-3, 5, -8, 8, 6, -6}));
        System.out.println(arrayAverage(new int[]{-3, 5, -8, 8, 6, -6}));
    }

    private static int exponentiation(int base, int exponent) {
        //Сложность O(exponent) для всех n == exponent элементов применяется только одна операция result *= base;
        // NaN, +-infinity, double и др. особые случаи что непроходят в начальной школе не рассматривал, но они алгоритм не усложнят,
        // т.к. операции сравнения с ними не зависят от числа элементов полседовательности
        if (exponent == 0) return 1; //не зависят от числа элементов полседовательности

        boolean isPositive = exponent > 0; // не зависят от числа элементов полседовательности
        exponent = exponent < 0 ? -exponent : exponent; // не зависят от числа элементов полседовательности
        int result = base;
        for (int i = 1; i < exponent; i++) {// не понял почуму, но итерация не влияет на сложность алгоритма, если можно повторите пожалуйта на уроке
            result *= base;
        }
        return isPositive ? result : 1 / result; // не зависят от числа элементов полседовательности
    }

    private static int arrayMinimum(int a[]) {
        //Сложность O(a.length).
        int min = Integer.MAX_VALUE; // не зависят от числа элементов полседовательности
        for (int i = 0; i < a.length; i++) { // итерация не влияет на сложность алгоритма
            min = a[i] < min ? a[i] : min;
        }
        return min;
    }

    private static double arrayAverage(int a[]) {
        //Сложность O(a.length).
        int sum = 0; // не зависят от числа элементов полседовательности
        for (int i = 0; i < a.length; i++) { // итерация не влияет на сложность алгоритма
            sum += a[i];
        }
        return (double) sum / a.length; // не зависят от числа элементов полседовательности
    }
}
