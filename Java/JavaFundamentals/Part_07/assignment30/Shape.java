package assignment30;

public abstract class Shape {

    private String name;

    Shape(String name) {
        this.name = name;
    }

    abstract void draw();
    abstract double calculateArea();
    abstract double calculatePerimeter();

    boolean isPositive(double number) {
        return number > 0 ;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
