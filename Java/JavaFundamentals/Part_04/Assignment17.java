public class Assignment17 {

    public static void main(String[] args) {
        int[] aInts = {2008, 1950, 1960, 1970, 1980, 1990, 2000, 2010, 2020};
        boolean yearCheck = false;
        for (int year : aInts) {
            yearCheck = isLeapYear(year);
            if (yearCheck){
                System.out.println(year + " is een schrikkeljaar");
            } else {
                System.out.println(year + " is GEEN schrikkeljaar");
            }
        }
    }

    private static boolean isLeapYear(int year){
        boolean isLeapYear = false;

        if (year % 4 == 0) {
            isLeapYear = true;
            if (year % 400 == 0) {
                isLeapYear = false;
            }
        }

        return isLeapYear;
    }
}
