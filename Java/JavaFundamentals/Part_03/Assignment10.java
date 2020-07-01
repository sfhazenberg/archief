public class Assignment10 {

    //private static int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static int[] intArray = {12, 45, 6, 87, 453, 3, 90, 89};

    public static void main(String[] args) {
        EvenOrOdd();
        FizzBuzz();   //geen idee waarom maar deze opdracht deed me aan FizzBuzz denken
    }

    private static void EvenOrOdd() {
        for(int value : intArray) {     //foreach om elk element v/d array te checken. Lijkt me het meest efficiënt
            if (value % 2 == 0) {       //als modulo 2 niet nul oplevert, dan is het een oneven getal. Bij een even getal blijft er geen restwaarde over is; modulo 2 geeft dan 0.
                System.out.println("Number " + value + " is EVEN");
            } else {
                System.out.println("Number " + value + " is ODD.");
            }
        }
        System.out.println("------------------------------");
    }

    private static void FizzBuzz() {
        int i = 1;
        do {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    System.out.println("FizzBuzz " + i);
                } else {
                    System.out.println("Fizz " + i);
                }
            } else if (i % 5 == 0) {
                System.out.println("Buzz " + i);
            } else {
                System.out.println(i);
            }
            i++;
        } while (i < 101);
    }
}
