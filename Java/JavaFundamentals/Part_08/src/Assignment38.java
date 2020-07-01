import java.util.HashMap;

public class Assignment38 {

    public static void main(String[] args) {
        HashMap<Integer, String> shapes = new HashMap<>();
        shapes.put(1, "Rectangle");
        shapes.put(2, "Circle");
        shapes.put(3, "Triangle");
        shapes.put(4, "Square");
        shapes.put(5, "Octagon");
        shapes.put(6, "Ellipse");
        System.out.println(shapes);

        shapes.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

}
