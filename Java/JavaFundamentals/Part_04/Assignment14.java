public class Assignment14 {

    public static void main(String[] args) {
        int[] aInts = {10, -3, -51, 62, 23, 48, 777, 9, -809};
        boolean checker = false;
        for(int value: aInts){
            checker = isEven(value);
            if (checker) {
                System.out.println("The number " + value + " is even");
            } else {
                System.out.println("The number " + value + " is odd");
            }
        }
    }

    private static boolean isEven(int a) {
        boolean isEven;
        if (a % 2 == 0) {
            isEven = true;
        } else {
            isEven = false;
        }
        return isEven;
    }
}
