public class Assignment20 {

    public static void main(String[] args) {
        System.out.println("First: "  + multiply(3, 5));
        System.out.println("Second: " + multiply(4, 7, 9));
        System.out.println("Third: "  + multiply(9, 4.6));
        System.out.println("Fourth: " + multiply(2.9, 6));
        System.out.println("Fifth: "  + multiplay(3.4, 7.6, 8.2, 9.1, 2.5));
        System.out.println("Sixth: "  + multiplay(6, 19, 34, 8));
    }

    private static double multiply(int a, int b) {
        return a*b;
    }

    private static double multiply(int a, int b, int c) {
        return a*b*c;
    }

    private static double multiply(int a, double b) {
        return a*b;
    }

    private static double multiply(double a, int b) {
        return a*b;
    }

    private static double multiplay(double... numbers) {
        double sum = 1;
        for (double i : numbers) {
            sum *= i;
        }
        return sum;
    }

    private static int multiplay(int a, int b, int c, int d) {
        return a*b*c*d;
    }
}
