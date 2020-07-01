public class Assignment01 {

    public static void main(String[] args) {
        InitialiseVars();
    }

    //byte -> short -> char -> int -> long -> float -> double
    private static void InitialiseVars() {
        /*
         * byte
         * Min: -128
         * Max: 127
         * Default: 0
         */
        byte byteVar = 48;
        System.out.println("byteVar is: " + byteVar);

        /*
         * short
         * Min: -2^15
         * Max: 2^15 - 1
         * Default: 0
         */
        short shortVar = 256;
        System.out.println("shortVar is: " + shortVar);

        /*
         * char
         * 16-bit Unicode UTF-16 character
         * Default: '\u0000'
         */
        char charVar = '\u1233';
        System.out.println("char is: " + charVar);

        /*
         * int
         * Min: -2^31
         * Max: 2^31 - 1
         * Default: 0
         */
        int intVar = 2048;
        System.out.println("intVar is: " + intVar);

        /*
         * long
         * Min: -2^63
         * Max: 2^63 - 1
         * Default: 0L
         */
        long longVar = 690420L;
        System.out.println("longVar is: " + longVar);

        /*
         * float
         * Min: 2^-149
         * Max: (2-2^-23)·2^127
         * Default: 0.0f
         */
        float floatVar = 46.2f;
        System.out.println("floatVar is: " + floatVar);

        /*
         * double
         * Min: 2^-1074
         * Max: (2-2^-52)·2^1023
         * Default: 0.0
         */
        double doubleVar = 7654321;
        System.out.println("doubleVar is: " + doubleVar);
    }
}
