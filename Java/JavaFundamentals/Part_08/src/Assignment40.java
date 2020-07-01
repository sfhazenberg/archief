import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Assignment40 {

    public static void main(String[] args) {
        HashMap<Integer, String> colorMap = new HashMap<>();
        colorMap.put(1, "Red");
        colorMap.put(2, "Green");
        colorMap.put(3, "Orange");
        colorMap.put(4, "White");
        colorMap.put(5, "Black");
        System.out.println(colorMap);   //40.2.c

        printHorLine();

        //40.2.e
        //interessante stackoverflow-pagina: https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        //forEach
        /*System.out.println("forEach");
        for (String color : colorMap.values()) {
            System.out.println(color);
        }*/
        //lambda
        System.out.println("lambda");
        colorMap.forEach((key, color) -> System.out.println(key + " -> " + color));

        printHorLine();

        //40.2.g
        //Intellij suggereerde om lambda expression te vervangen met method reference.
        //relevante SO-pagina, verheldert het gebruik van "::": https://stackoverflow.com/questions/31020269/what-is-the-use-of-system-outprintln-in-java-8
        System.out.println("entrySet");
        colorMap.entrySet().forEach(System.out::println);

        printHorLine();

        //40.2.i
        System.out.println("keySet");
        colorMap.keySet().forEach(key -> System.out.println(key + " -> " + colorMap.get(key)));

        printHorLine();

        //40.2.k
        System.out.println("The size of the hashmap is: " + colorMap.size());

        printHorLine();

        //40.3.a
        System.out.println("Does the color map contain the key 6? " +
                (colorMap.containsKey(6) ? "TRUE" : "FALSE"));
        //alternatieve manier
        /*boolean keyCheck = (colorMap.containsKey(6));
        System.out.println(String.format(
                "Does the color map contain the key 6? {%b}",
                keyCheck
        ));*/

        //40.3.b
        System.out.println("Does the color map contain the color Red? " +
                (colorMap.containsValue("Red") ? "TRUE" : "FALSE"));

        //40.4.a
        System.out.println("The color with key 3 is: " + colorMap.get(3));
        //40.4.b
        System.out.println("The color with key 2 is: " + colorMap.get(2));
        //40.4.c
        System.out.println("The colorMap contains these keys: " + colorMap.keySet());
        //40.4.d
        System.out.println("The colorMap contains these values: " + colorMap.values());

        colorMap.clear();
        System.out.println(colorMap);
        System.out.println("Is the color map empty? " + ((colorMap.size() > 0) ? "TRUE" : "FALSE"));
    }

    private static void printHorLine() {
        System.out.println("──────────────────────────────────────────────────");
    }

}
