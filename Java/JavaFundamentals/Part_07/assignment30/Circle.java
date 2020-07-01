package assignment30;

public class Circle extends Shape {

    private double radius;

    Circle(double radius) {
        super("Circle");
        setRadius(radius);
    }

    @Override
    void draw() {
        System.out.println("Drawing a circle...");
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter() {
        return 2.0 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = isPositive(radius) ? radius : 0;
    }
}
