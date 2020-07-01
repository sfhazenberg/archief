import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;

public class Assignment39 {

    public static void main(String[] args) {
        ArrayList<String> colorList = new ArrayList<>();
        Collections.addAll(colorList, "Red", "Green", "Orange", "White", "Black");
        System.out.println(colorList);

        printHorLine();

        colorList.forEach(color -> System.out.println(color));

        printHorLine();

        System.out.println("The size of the array list is " + colorList.size());

        printHorLine();

        colorList.add(0, "Pink");
        colorList.add(5, "Yellow");
        System.out.println(colorList);

        printHorLine();

        System.out.println("Color at index 0 is: " + colorList.get(0));
        System.out.println("Color at index 3 is: " + colorList.get(3));

        printHorLine();

        colorList.set(5, "Blue");
        System.out.println(colorList);

        printHorLine();

        colorList.remove(4);
        System.out.println(colorList);

        printHorLine();

        System.out.println(colorList.contains("Green") ? "Found the color Green" : "Green not found");

        printHorLine();

        System.out.println("ColorList before sort: " + colorList);
        Collections.sort(colorList);
        System.out.println("ColorList after sort: " + colorList);
    }

    private static void printHorLine() {
        System.out.println("──────────────────────────────────────────────────");
    }
}
