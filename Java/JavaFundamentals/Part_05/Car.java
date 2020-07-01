class Car {

    private String name;
    private int currentSpeed = 0;
    private int topSpeed;

    void setName(String name) {
        this.name = name;
    }

    void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    String getName() {
        return name;
    }

    int getCurrentSpeed() {
        return currentSpeed;
    }

    int getTopSpeed() {
        return topSpeed;
    }

    void increaseSpeed(int input) {
        //input MOET plus zijn
        //currentSpeed mag niet groter dan topSpeed
        if (input > 0) {
            currentSpeed += input;
            if (currentSpeed > topSpeed) {
                currentSpeed = topSpeed;
                System.out.println("Top speed has been reached: " + topSpeed + " km/h");
            }
        } else {
            System.out.println("Cannot decrease speed with this method.");
        }

    }
}
