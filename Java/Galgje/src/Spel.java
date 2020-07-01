import java.util.*;

class Spel{

    private String woord;
    private String rijFouten;
    private char[] woordChars;
    private char[] underscoreArray;
    private List<Character> arrFout = new ArrayList<>();
    private int counter;
    private int overigeUnderscores;
    private boolean wordGuessed;

    /**
     * Invoer voor het te raden woord. Checkt op lege/ongeldige invoer.
     * @param name de naam die in de Main-klasse is ingevoerd
     */
    Spel(String name) {
        counter = 0;
        overigeUnderscores = 0;
        wordGuessed = false;

        System.out.println("Okee, welk woord gaat " + name + " pogen om te raden?");

        while (true) {
            Scanner inputWord = new Scanner(System.in);
            woord = inputWord.nextLine();
            if (woord.isEmpty() || woord.isBlank() || woord.matches(".*\\d.*")) {
                System.out.println("Ongeldige invoer, probeer het nog eens.");
            } else {
                woord = woord.toLowerCase();
                prepWoord(woord);
                break;
            }
        }
    }

    /**
     * Construeert array met losse letters v/h woord en een array van lage streepjes die gelijk is aan de lengte v/h woord.
     * @param woord die in de constructor wordt ingevoerd.
     */
    private void prepWoord(String woord){
        this.woordChars = woord.toCharArray();
        String underscores = woord.replaceAll("[a-zA-Z]", "_");
        this.underscoreArray = underscores.toCharArray();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        lettersRaden();
    }

    /**
     * Vraagt om invoer zodat het woord kan worden geraden.
     */
    private void lettersRaden(){
        do {
            if(rijFouten != null){
                printFouteLetters();
            }

            constructWord();

            if(counter == 0){
                System.out.println("Je mag 10 fouten maken.");
            } else if (counter == 9){
                System.out.println("Laatste kans!");
            } else {
                System.out.println("Je mag nog " + (10 - counter) + " fouten maken.");
            }

            System.out.println("Welke letter wil je raden?");

            while(true){
                Scanner keyboard = new Scanner(System.in);
                String geradenLetter = keyboard.nextLine();
                if(geradenLetter.length() > 1) {
                    if(geradenLetter.equals(woord)){
                        System.out.println("Hah, slimmerik!");
                        wordGuessed = true;
                        winGame();
                    } else {
                        System.out.println("Gaarne 1 letter invoeren");
                    }
                } else if (!Character.isLetter(geradenLetter.charAt(0))){   //dekt ongeldige invoer af.
                    System.out.println("Da's geen letter, probeer het nog eens");
                } else {
                    char c = geradenLetter.charAt(0);
                    checkLetter(c);
                    break;
                }
            }
        } while (counter < 10);
    }

    /**
     * Controleert of invoer in het woord voorkomt.
     * @param geradenLetter ingevoerd om het woord te raden.
     */
    private void checkLetter(char geradenLetter){
        boolean goedGeraden = false;

        for(int i = 0; i < woordChars.length; i++)
            if (woordChars[i] == geradenLetter) {
                if(underscoreArray[i] == geradenLetter) {    //checkt of CORRECTE input al eerder is ingevoerd.
                    System.out.println("Letter al eerder geraden, probeer opnieuw.");
                    lettersRaden();
                } else {
                    this.underscoreArray[i] = geradenLetter;    //zo niet, gooit correct geraden letter in het woord.
                    overigeUnderscores++;
                    goedGeraden = true;
                }
            } else {
                if(arrFout.contains(geradenLetter)){    //checkt of FOUTIEVE input al eerder is ingevoerd.
                    System.out.println("Foutief letter al eerder geraden, probeer opnieuw.");
                    lettersRaden();
                }
            }

        if(goedGeraden){
            System.out.println("Correct!");
            if(overigeUnderscores == woordChars.length){    //checkt of woord volledig is ingevuld.
                winGame();                                  //zo ja, dan is het spel gewonnen.
            }
        } else {
            hangDeMan();
            arrayFouteLetters(geradenLetter);
        }

        lettersRaden();
    }

    /**
     * Gooit foutieve letters in array
     * @param fouteLetter die niet in het woord voorkomt.
     */
    private void arrayFouteLetters(char fouteLetter){
        arrFout.add(fouteLetter);

        StringBuilder builder = new StringBuilder();
        for(char fout : arrFout){
            builder.append(fout).append(" ");
        }
        this.rijFouten = builder.toString();
    }

    /**
     * Print foutieve letters.
     */
    private void printFouteLetters(){
        System.out.println("Foutieve letters: " + this.rijFouten);
    }

    /**
     * Construeert het huidige woord
     */
    private void constructWord(){
        if(wordGuessed){
            woord = woord.replaceAll(".(?=.)", "$0 ");
            printenWoord(woord + " ");
        } else {
            StringBuilder message = new StringBuilder();
            for (char letter : this.underscoreArray) {
                message.append(letter).append(" ");
            }
            String incompleteWord = message.toString();
            printenWoord(incompleteWord);
        }
    }

    /**
     * Print het huidige woord in een rechthoek.
     * @param message is hetgene dat als woord geprint wordt.
     */
    private void printenWoord(String message){
        int widthBox = message.length() + 1;
        String boxHorLine = "─".repeat(widthBox);

        System.out.println("┌" + boxHorLine + "┐"); //\
        System.out.println("│ " + message + "│");   //print box met HET woord
        System.out.println("└" + boxHorLine + "┘"); ///
    }

    /**
     * Construeert de galg indien een foutieve letter wordt ingevoerd.
     */
    private void hangDeMan(){
        this.counter++;

        System.out.println("Zit er niet in.");

        switch(counter){
            case 1:
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 2:
                System.out.println("  ┌");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 3:
                System.out.println("  ┌──────");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 4:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 5:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │      ■");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 6:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │      ■");
                System.out.println("  │     /");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 7:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │      ■");
                System.out.println("  │     /|");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 8:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │      ■");
                System.out.println("  │     /|\\");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 9:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │      ■");
                System.out.println("  │     /|\\");
                System.out.println("  │     /");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                break;
            case 10:
                System.out.println("  ┌──────┐");
                System.out.println("  │      │");
                System.out.println("  │      ■");
                System.out.println("  │     /|\\");
                System.out.println("  │     / \\");
                System.out.println("  │");
                System.out.println("  │");
                System.out.println("┌─┴────────────┐");
                System.out.println("│              │");
                System.out.println("└──────────────┘");
                System.out.println("OEF, je hangt!");
                System.out.println("Het woord was: " + woord);
                endGame();
                break;
        }
    }

    /**
     * Print overwinningstekst indien gewonnen.
     */
    private void winGame(){
        constructWord();
        System.out.println("┌────────────────────────────┐");
        System.out.println("│ Je hebt het woord geraden! │");
        System.out.println("└────────────────────────────┘");
        endGame();
    }

    /**
     * Stopt de applicatie.
     */
    private void endGame(){
        System.exit(1);
    }
}
