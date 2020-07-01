public class Assignment08 {

    public static void main(String[] args) {
        ifElseIfElse();
        switchDahooky();
        ternaryMahjigger();
    }

    private static void ifElseIfElse() {
        String name = "John";
        //String name = "Bob";

        if (name.equals("John")) {
            System.out.println("His name is John");
        } else if (name.equals("Doe")) {
            System.out.println("His name is Doe");
        } else {
            System.out.println("His name is neither John nor Doe");
        }
    }

    private static void switchDahooky() {
        //String name = "John";
        String name = "Bob";

        switch (name) {
            case "John": {
                System.out.println("His name is John");
                break;
            }
            case "Doe": {
                System.out.println("His name is Doe");
                break;
            }
            default: {
                System.out.println("His name is neither John nor Doe");
            }
        }
    }

    private static void ternaryMahjigger() {
        int a = 10;
        int b = -21;

        //het kleinste getal bepaald van de a en b en print
        final String msg = b < a
                ? "b is smaller than a"
                : "a is smaller than b";
        System.out.println(msg);
        //System.out.println(b < a ? "b is smaller than a" : "a is smaller than b");

        //het grootste getal bepaald van a en b en print
        final String msg2 = b > a
                ? "b is larger than a"
                : "a is larger than b";
        System.out.println(msg2);

        //de absolute waarde van b bepaald en print
        //TODO: smijt hier een ternary operator in
        final String msg3 = b < 0
                ? ""  //dan is b kleiner dan 0
                : ""; //anderzijds is b groter dan 0

        System.out.println("The absolute value of b is: " + Math.abs(b));
    }
}