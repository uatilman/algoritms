//Иван, добрый день!
//Задачу про рюкзак не успел осилить, ниже привожу, то что сделал.
//Не работает так как ignoreIndex перемещается справа от корневого элемена (startIndex),
// а необходимо пробовать занулить все комбинации провее узла. Но что-то у меня мозг не успел дойти до этого.

//**** Написать программу «Задача о рюкзаке» с помощью рекурсии. В общем виде задачу можно сформулировать так:
// из заданного множества предметов со свойствами «стоимость» и «вес» требуется отобрать подмножество с максимальной полной стоимостью,
// соблюдая при этом ограничение на суммарный вес. Вывести итоговую стоимость "рюкзака".
// Нерекурсивное решение и рекуррентные соотношения в википедии
public class Main {

    private static int maxW = 0;
    private static int currentMaxPrice = 0;

    private static final int MAX_WEIGHT = 25;
    private static int[] weights = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private static int[] prices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


    private static int getMaxPrice(int ignoreIndex, int startIndex) {
        if (startIndex == ignoreIndex) ignoreIndex++;
        if (startIndex == weights.length) return currentMaxPrice;
        int sumWeight = 0;
        int sumPrice = 0;
        int i = startIndex;
        while (sumWeight <= MAX_WEIGHT && i < weights.length) {
            if (i == ignoreIndex) {
                i++;
                continue;
            }
            sumWeight += weights[i];
            sumPrice += prices[i];
            i++;
        }
        sumWeight -= weights[i - 1];
        sumPrice -= prices[i - 1];
        if (currentMaxPrice < sumPrice) {
            currentMaxPrice = sumPrice;
            maxW = sumWeight;
        }

        if (ignoreIndex + 1 >= weights.length)
            return getMaxPrice(0, startIndex + 1);
        else
            return getMaxPrice(ignoreIndex + 1, startIndex);
    }


    public static void main(String[] args) {

        System.out.println("price " + getMaxPrice(0, 0));
        System.out.println("weights " + maxW);
    }
}
