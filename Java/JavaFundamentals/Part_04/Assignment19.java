import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Assignment19 {

    public static void main(String[] args) {
        double[] aDoubles = {3.0, 7.2, 5.4, 9.1, 4.2, 6.8, 2.6, 8.9};
        multiplyDoubles(3, aDoubles);

        //multiplyDoubles(3, aDoubles);
        //sout

        //multiplyDoubles(5, aDoubles);
        //sout
    }

    //gebruikte stackoverflow: https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array
    //currentTimeMillis genoemd in bovenstaande draad. Geen idee hoe het precies werkt, maar pakt een willekeurige waarde uit aDoubles.

    private static double multiplyDoubles(int amount, double... a) {
        //TODO: dit fixen
        double multiplication = 0;
        double[] aTemp = new double[amount];
        //Random r = new Random();
        //int l = a.length;

        for (int i = 0; i < amount; i++){
            double rnd = a[(int)(System.currentTimeMillis() % a.length)];
            aTemp[i] = rnd;
        }

        System.out.println(aTemp);

        return multiplication;
    }
}
