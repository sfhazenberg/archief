public class Assignment06 {

    public static void main(String[] args) {
        Func1();
        Func2();
        Func3();
        Func4();
    }

    private static void Func1() {
        String helloWorld = "Hello Java World";
        StringBuffer buffer = new StringBuffer();
        buffer.append(helloWorld).append(", programming in Java is fun");
        System.out.println(buffer);
    }

    private static void Func2() {
        String helloWorld = "Hello Java World!";
        StringBuilder builder = new StringBuilder();
        builder.append(helloWorld);
        builder.delete(6, 11);
        System.out.println(builder);
    }

    private static void Func3() {
        int value = 10;
        double dblValue = value;
        System.out.println("doubleValue is: " + dblValue);
    }

    private static void Func4() {
        double value = 11.89;
        int intValue = (int) value;
        System.out.println("intValue is: " + intValue);
    }
}
