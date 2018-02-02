package lesson;

import lesson.Bracket;
import lesson.Queue;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Bracket.check("5{5}5{5(5)5[5(5<5>5)5{5}5]5}5")
        );

        Queue q = new Queue(10);
        for (int i = 0; i < 5; i++) {
            q.insert(i);
        }
        for (int i = 0; i < 11; i++) {
            System.out.println(q.remove());
        }


    }

}
