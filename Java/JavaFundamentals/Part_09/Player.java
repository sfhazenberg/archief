package assignment42;

import java.util.Scanner;

class Player {

    Player(){
        askName();
    }

    private void askName(){
        System.out.println("What is your name?");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            if (name.isBlank() || name.isEmpty()) {
                System.out.println("Invalid input, try again");
            } else {
                name = name.substring(0,1).toUpperCase() + name.substring(1);
                System.out.println("Your name is: " + name);
                break;
            }
        }
    }
}
