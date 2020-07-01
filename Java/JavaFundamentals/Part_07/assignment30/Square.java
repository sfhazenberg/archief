package assignment30;

public class Square extends Shape {

    private double side;

    Square(double side) {
        super("Square");
        setSide(side);
    }

    @Override
    void draw() {
        System.out.println("Drawing a square...");
    }

    @Override
    double calculateArea() {
        return side * side;
    }

    @Override
    double calculatePerimeter() {
        return 4 * side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = isPositive(side) ? side : 0;
    }
}
