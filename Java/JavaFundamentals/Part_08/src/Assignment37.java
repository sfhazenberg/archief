import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Assignment37 {

    public static void main(String[] args) {
        printGloriousVehicles();
        printBunchaNumbers();
    }

    private static void printGloriousVehicles() {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("Honda");
        cars.add("Toyota");
        cars.add("Nissan");
        cars.add("Mazda");
        cars.add("Subaru");
        cars.forEach(car -> System.out.println(car));
    }

    private static void printBunchaNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 5, 42, 63, 71, 12, -31, 4);
        System.out.println("Minimum number is: " + Collections.min(numbers));
        System.out.println("Maximum number is: " + Collections.max(numbers));
        Collections.sort(numbers);
        System.out.println("SORTED - " + numbers);
    }
}
