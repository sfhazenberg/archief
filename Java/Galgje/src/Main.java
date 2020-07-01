import java.util.Scanner;

public class Main {

    /**
     * Vraagt om naam en instantieert dan een Spel-klasse.
     */
    public static void main(String[] args) {
        System.out.println("Welkom bij Galgje! Wie gaat een verwoede poging doen om het woord raden vandaag?");

        while (true) {
            Scanner inputName = new Scanner(System.in);
            String input = inputName.nextLine();
            if (input.isEmpty() || input.isBlank()) {
                System.out.println("Leegte is geen naam, probeer het nog eens.");
            } else {
                String name = input.substring(0,1).toUpperCase() + input.substring(1);
                new Spel(name);
                break;
            }
        }
    }
}
