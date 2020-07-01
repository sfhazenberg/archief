class Point {

    private int x;
    private int y;

    Point() {
        System.out.println("The default constructor has been used.");
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Parameterized constructor has been used.");
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void printCoordinates() {
        System.out.println("The x value is " + getX() + " and the y value is " + getY());
    }
}
