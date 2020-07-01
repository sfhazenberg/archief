package assignment32;

public class Assignment32 {

    public static void main(String[] args) {
        for (Month m : Month.values()) {
            System.out.println(String.format(
                    "Month number of %s is %s and has %d days",
                    m,
                    m.getMonthNumber(),
                    m.getNumberOfDays()
            ));
        }
    }
}
