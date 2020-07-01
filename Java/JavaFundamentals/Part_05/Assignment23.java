public class Assignment23 {

    public static void main(String[] args) {
        Car bmw = new Car();
        bmw.setName("BMW");
        bmw.setTopSpeed(250);
        System.out.println(String.format(
                "This %s has a current speed of %d km/h and has a top speed of %d km/h.",
                bmw.getName(),
                bmw.getCurrentSpeed(),
                bmw.getTopSpeed()
        ));
        bmw.increaseSpeed(50);
        System.out.println("Current speed of BMW is: " + bmw.getCurrentSpeed() + " km/h");

        System.out.println("");

        Car ferrari = new Car();
        ferrari.setName("Ferrari");
        ferrari.setTopSpeed(300);
        System.out.println(String.format(
                "This %s has a current speed of %d km/h and has a top speed of %d km/h.",
                ferrari.getName(),
                ferrari.getCurrentSpeed(),
                ferrari.getTopSpeed()
        ));
        ferrari.increaseSpeed(325);
        System.out.println("Current speed is of Ferrari is: " + ferrari.getCurrentSpeed() + " km/h");

        System.out.println("");

        bmw.increaseSpeed(-10);
        System.out.println("Current speed of BMW is: " + bmw.getCurrentSpeed() + " km/h");
    }
}
