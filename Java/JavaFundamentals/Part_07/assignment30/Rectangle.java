package assignment30;

public class Rectangle extends Shape {

    private double width;
    private double height;

    Rectangle(double width, double height) {
        super("Rectangle");
        setWidht(width);
        setHeight(height);
    }

    @Override
    void draw() {
        System.out.println("Drawing a rectangle...");
    }

    @Override
    double calculateArea() {
        return width * height;
    }

    @Override
    double calculatePerimeter() {
        return 2.0 * (width + height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = isPositive(width) ? width : 0;
    }

    public void setHeight(double height) {
        this.height = isPositive(height) ? height : 0;
    }
}
