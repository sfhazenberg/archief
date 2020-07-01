public class Assignment03 {

    public static void main(String[] args) {
        WideningTypeCasting();
        NarrowingTypeCasting();
    }

    private static void WideningTypeCasting() {
        int myInt1 = 100;
        long myLong1 = myInt1;      //widening cast from int to long
        float myFloat1 = myLong1;   //widening cast from long to float
        System.out.println("WIDE - myInt1 is: " + myInt1);
        System.out.println("WIDE - myLong1 is: " + myLong1);
        System.out.println("WIDE - myFloat1 is: " + myFloat1);
    }

    private static void NarrowingTypeCasting() {
        double myDouble2 = 66.78d;
        long myLong2 = (long)myDouble2; //narrowing cast from double to long
        int myInt2 = (int)myLong2;      //narrowing cast from long to int
        System.out.println("NARROW - myDouble2 is: " + myDouble2);
        System.out.println("NARROW - myLong2 is: " + myLong2);
        System.out.println("NARROW - myInt2 is: " + myInt2);
    }
}
