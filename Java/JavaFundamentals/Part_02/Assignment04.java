import java.util.Random;
import java.util.Scanner;

/*  Gebruik de onderstaande dingen
 •v	String length() - length of the string
 •v	String charAt() - returns a char value at the given index number
 •v	String concat() - combines specified string at the end of this string
 •v	String contains() - returns true if chars are found in the string
 •v	String startsWith() - checks if this string starts with given prefix
 •v	String endsWith() - checks if this string ends with given suffix
 •v	String equals() - compares the contents of two given strings
 •v	String indexOf() - returns index of given character value or substring
 •v	String isEmpty() - checks if this string is empty
 •v	String replace() - returns a string replacing all the old char to new char
 •v	String substring() - returns a part of the string
 •v	String toLowerCase() - returns the string in lowercase letter
 •v	String toUpperCase() - returns the string in uppercase letter
 •v	String trim() - eliminates leading and trailing spaces
 */

public class Assignment04 {

    public static void main(String[] args) {
        TheMothership();
    }

    private static void TheMothership() {
        Scanner scanner = new Scanner(System.in);
        boolean isWrongAnswer;
        do {
            isWrongAnswer = false;
            System.out.println("Which function do you want to give some attention?");
            System.out.println("1: StringLength()");
            System.out.println("2: StringChar()");
            System.out.println("3: StringConcat()");
            System.out.println("4: theHamCheck()");
            System.out.println("5: StringStartsWith()");
            System.out.println("6: StringReplace()");
            System.out.println("7: TheTragedyOfDarthPlagueisTheWise()");
            int number = scanner.nextInt();
            switch (number){
                case 0:
                    System.exit(0);
                case 1:
                    StringLength();
                    break;
                case 2:
                    StringChar();
                    break;
                case 3:
                    StringConcat();
                    break;
                case 4:
                    theHamCheck();
                    break;
                case 5:
                    StringStartsWith();
                    break;
                case 6:
                    StringReplace();
                    break;
                case 7:
                    TheTragedyOfDarthPlagueisTheWise();
                    break;
                default:
                    System.out.println("Choose 1 of 6 options");
                    isWrongAnswer = true;
            }
        } while (isWrongAnswer);
        //bovenstaande logica gejat van: https://stackoverflow.com/questions/52269018/scanner-switch-use
    }

    private static void StringLength() {
        String lengthCheck = "The length of this string will be checked.";
        int lengthOfStr = lengthCheck.length();
        System.out.println(lengthCheck);
        System.out.println("Length of above string is: " + lengthOfStr);

        TheMothership();
    }

    private static void StringChar() {
        String charCheck = "A random character will be grabbed from this string.";
        Random r = new Random();        //vraag me niet waarom hier RNG is
        int begin = 0;
        int end = charCheck.length();
        int result = r.nextInt(end-begin);
        char c = charCheck.charAt(result);
        int indexC = charCheck.indexOf(c);
        System.out.println("Character is: " + c + " at index " + indexC);

        TheMothership();
    }

    private static void StringConcat() {
        String movie = "Fast and Furious";
        movie = movie.concat(" - Electric Boogaloo");
        System.out.println("Hey let's go watch " + movie + " tonight!");

        TheMothership();
    }

    private static void theHamCheck() {
        Scanner scanner = new Scanner(System.in);
        String joy = "There's ham on my sandwich! :D";
        String mad = "WHY NO HAM NOW I'M MAD >:(";
        System.out.println("Does the sandwich have ham on it? (y/n)");

        while (true) {
            String response = scanner.nextLine();
            if (response.equals("y")) {
                System.out.println(joy.toUpperCase());  //yay ham!
                break;
            } else if (response.equals("n")) {
                System.out.println(mad.toLowerCase());  //boo no ham
                break;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }

        TheMothership();
    }

    private static void StringStartsWith() {
        String movie = "Hobbs and Shaw";
        boolean prefix = movie.startsWith("Hobbs");
        boolean suffix = movie.endsWith("Shaw");
        if (prefix && suffix) {
            System.out.println("The recent Fast and Furious spin-off is called " + movie);
        }

        TheMothership();
    }

    private static void StringReplace() {
        Scanner scanner = new Scanner(System.in);
        String line = "Hello, is this the Krusty Krab?";
        System.out.println(line);
        System.out.println("Input two letters (ab). The latter will replace the former in the above sentence. Input 'exit' to exit.");
        while (true) {
            String response = scanner.nextLine();
            if (response.length() == 2) {
                System.out.println(line.replace(response.charAt(0), response.charAt(1)));
            } else if (response.equals("exit")) {
                System.out.println("Exiting while loop");
                break;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }

        TheMothership();
    }

    private static void TheTragedyOfDarthPlagueisTheWise() {
        String line1 = "Have you ever heard the tragedy of Darth Plagueis the Wise?";
        String line2 = "I thought not. It is not a tale the Jedi would tell you.";
        String line3 = "    Oy, you didn't let me DO IT D:<         ";
        System.out.println(line1);
        System.out.println("Quick, hit a button to shut Sheev up!");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println(line2.substring(0,19) + "-");
        System.out.println(line3.trim());

        TheMothership();
    }
}
