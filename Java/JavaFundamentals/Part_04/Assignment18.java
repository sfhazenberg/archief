public class Assignment18 {

    public static void main(String[] args) {
        int[] aInts = {4, 7, 12, 28, 39, 40, 42, 67, 89, 914};
        double averageOfArray = average(aInts);
        System.out.println("The average of aInts is: " + averageOfArray);
    }

    private static double average(int[] aInts) {
        double sum = 0;
        for (double number : aInts) {
            sum += number;
        }
        System.out.println("The sum of aInts is: " + sum);
        return sum / aInts.length;
    }
}
