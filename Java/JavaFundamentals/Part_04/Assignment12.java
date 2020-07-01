public class Assignment12 {

    public static void main(String[] args) {
        //System.out.println("Is this the Krusty Krab?");
        //System.out.println("No, this is Patrick!");
        printGreetingMessageToConsole();
        System.out.println(getJavaGreeting());
        printNameToConsole("Obi-Wan Kenobi");
        System.out.println("The sum of 10 and 20 is " + sumOfIntegers(10, 20));
    }

    //varargs
    //voor tijdens debuggen: method selecteren en dan Alt+F8
    //Alt+linkerklik op een method geeft de waarde terug

    /**
     * Method which prints General Keno- UUUUH a greeting message to the console.
     */
    private static void printGreetingMessageToConsole() {
        System.out.println("Hello there!");
    }

    /**
     * Method which returns a bold one's greeting.
     * @return the greeting string
     */
    private static String getJavaGreeting() {
        return "General Kenobi!";
    }

    /**
     * Method which prints the users name
     * @param name the name printed to the console
     */
    private static void printNameToConsole(String name) {
        System.out.println("General " + name + ", you are a bold one!");
    }

    /**
     * Method that sums up two integers and returns the result
     * @param a the first integer, one half of the result
     * @param b the second integer, the other half of the result
     * @return the result of a and b summed up
     */
    private static int sumOfIntegers(int a, int b) {
        return a + b;
    }
}
