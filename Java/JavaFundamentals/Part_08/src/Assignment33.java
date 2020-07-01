import java.util.Scanner;

public class Assignment33 {

    public static void main(String[] args) {
        userInput();
    }

    private static void userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("My dude, what do you go by these days?");
        String username = scanner.nextLine();

        System.out.println(scanner.hasNext(username) ? "Yeh boi" : "Bruh :(");
        //validateInput(username);

        System.out.println("What be your age? (in Earth years)");
        int age = scanner.nextInt();

        System.out.println("My fellow compatriot, how much dosh dost thou yield on a monthly basis?");
        double salary = scanner.nextDouble();

        System.out.println(String.format(
                "The name of the user is: %s \n" +
                "The age of the user is: %d \n" +
                "The salary of the user is: %f",
                username,
                age,
                salary
        ));
    }

    private static void validateInput(String input) {

    }

}
