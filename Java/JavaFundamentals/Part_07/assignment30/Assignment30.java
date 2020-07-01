package assignment30;

public class Assignment30 {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(20.5, 30.1);
        rectangle.draw();
        printShapeInfo(rectangle.getName(), rectangle.calculateArea(), rectangle.calculatePerimeter());

        Circle circle = new Circle(52.12);
        circle.draw();
        printShapeInfo(circle.getName(), circle.calculateArea(), circle.calculatePerimeter());

        Square square = new Square(20.0);
        square.draw();
        printShapeInfo(square.getName(), square.calculateArea(), square.calculatePerimeter());

        rectangle.setHeight(-3.6);
        circle.setRadius(-8.2);
        square.setSide(-7.9);
        printShapeInfo(rectangle.getName(), rectangle.calculateArea(), rectangle.calculatePerimeter());
        printShapeInfo(circle.getName(), circle.calculateArea(), circle.calculatePerimeter());
        printShapeInfo(square.getName(), square.calculateArea(), square.calculatePerimeter());
    }

    private static void printShapeInfo(String name, double area, double perimeter) {
        System.out.println(String.format(
                "The %s has an area of %.2f and a perimeter of %.3f",
                name,
                area,
                perimeter
        ));
    }
}
