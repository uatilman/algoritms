import java.util.Scanner;
/**
**** Создать программу, которая переворачивает вводимые строки
*/

public class InvertString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Input string or input \"\\end\" to end.");
            input = scanner.nextLine();
            if (input.equals("\\end")) break;
            System.out.println("revert result: " + revert(input));
        }
    }

    public static String revert(String src) {
        char ch;
        StringBuilder builder = new StringBuilder();
        for (int i = src.length() - 1; i >= 0; i--) {
            ch = src.charAt(i);
            builder.append(ch);
        }
        return builder.toString();
    }
}
