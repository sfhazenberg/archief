import java.util.Scanner;

public class Assignment34 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a number");
        int a = scanner.nextInt();
        System.out.println("And input another one");
        int b = scanner.nextInt();

        System.out.println("Result is: " + divide(a, b));
    }

    private static int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Can't divide by zero, returning 0");
            return 0;
        }
    }

}
