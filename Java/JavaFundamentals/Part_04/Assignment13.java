import java.util.Arrays;

public class Assignment13 {

    public static void main(String[] args) {
        System.out.println(calculator(4, 5, "+"));
        System.out.println(calculator(20, 7, "-"));
        System.out.println(calculator(3, 7, "*"));
        System.out.println(calculator(99, 6, "/"));
        System.out.println(calculator(66, 2, "I AM the Senate"));
    }

    /**
     * Method that accepts two doubles and calculates the outcome depending on the given operator
     *
     * @param a        the first double, one half of the result
     * @param b        the second double, the other half of the result
     * @param operator the operator needed to compute the two doubles
     * @return the result of the computation
     */
    private static double calculator(double a, double b, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                System.out.print("Not a valid operator, result defaulted to ");
        }

        return result;
    }
}
