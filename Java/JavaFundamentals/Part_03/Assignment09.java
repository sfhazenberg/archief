import java.util.stream.IntStream;

public class Assignment09 {

    private static int[] intArray = {5, 8, 4, 6, 9, 7};

    public static void main(String[] args) {
        While();
        DoWhile();
        For();
        ForEach();
        IntStream();
    }

    private static void While() {
        int sumWhile = 0;
        int i = 0;
        while (i < intArray.length) {
            sumWhile += intArray[i];
            i++;
        }
        System.out.println("WHILE - Sum is: " + sumWhile);
    }

    private static void DoWhile() {
        int i, sumDo;
        i = sumDo = 0;
        do {
            sumDo += intArray[i];
            i++;
        } while (i < intArray.length);
        System.out.println("DOWHILE - Sum is: " + sumDo);
    }

    private static void For() {
        int sumFor = 0;
        for (int j = 0; j < intArray.length; j++) {
            sumFor += intArray[j];
        }
        System.out.println("FOR - Sum is: " + sumFor);
    }

    private static void ForEach() {
        int sumForEach = 0;
        for (int value : intArray) {
            sumForEach += value;
        }
        System.out.println("FOREACH - Sum is: " + sumForEach);
    }

    private static void IntStream() {
        int sumStream = IntStream.of(intArray).sum();
        System.out.println("INTSTREAM - Sum is: " + sumStream);
    }
}
