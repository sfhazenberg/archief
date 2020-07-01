import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Assignment16 {

    // interessante stackoverflow-vraag hierover gevonden, gaat in op snelheid/prestaties van allerhande functies.
    // https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java

    private static List<Integer> primeRange = IntStream.rangeClosed(2, 100).boxed().collect(Collectors.toList());   //maakt een array van 2 tot 100

    public static void main(String[] args) {

        for (int i : primeRange){
            if(isPrime(i)){
                System.out.println("Is this the Krusty Krab?");
                //System.out.println("The number " + i + " is a prime");
            } else {
                System.out.println("No, this is Patrick!");
                //System.out.println("The number " + i + " is NOT a prime");
            }
        }
    }

    private static boolean isPrime(int a) {
        boolean isPrime = false;
        int j = 0;
        List<Integer> rangeToCheck = new ArrayList<>();
        //rangeToCheck.add(1);
        //rangeToCheck.add(1);
        for (int i = 2; i < a; i++){
            rangeToCheck.add(i);    //TODO: primeRange loopen door deze range. Als ie vaker dan 2x wordt gedeeld, dan isPrime = true.
            /* if(a % i == 0){
                j++;
                if (j >= 2) {
                    System.out.println(a + "is geen priemgetal");
                }
                System.out.println(a + " % " + i + " is nul. j is: " + j);
            } else {
                //System.out.println(a + " % " + i + " is NIET nul");
            } */
        }
        System.out.println("a is " + a);
        System.out.println(rangeToCheck);
        //System.out.println("Waarde van index " + a + " van rangeToCheck is " + rangeToCheck.get(a-1));
        for (int k = 0; k < a; k++) {
            System.out.println("a is: " + a);
            System.out.println("k is: " + k);
            if(a % rangeToCheck.get(k) == 0) {
                System.out.println("K uit rangeCheck is: " + k);
                j++;
            }
        }
        //if (j >= 2)
        //TODO: deel a door elke voorafgaande int. Als a = 4, deel dan a door 4, 3 en 2. Als ie in dat geval vaker dan 1x kan worden gedeeld, dan isPrime = true.
        //als a % i = 0, dan j++. Als j > 1, dan nokken en isPrime = false. (ervanuitgaande dat we de a % a al dekken)
        //maak een array en plak a eraan vast elke keer dat deze functie wordt aangeroepen
        //of: pak linkerhelft van primeRange met a-1 als bereik. (dus: als a = 3, pak dan 2 linker ints van primeRange en gebruik die voor de check)
        //gebruik modulus



        return isPrime;
    }
}
