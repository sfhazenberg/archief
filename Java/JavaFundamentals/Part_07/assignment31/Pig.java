package assignment31;

public class Pig implements Animal {

    @Override
    public void animalSound() {
        System.out.println("A pig says wee wee...");
    }

    @Override
    public void run() {
        System.out.println("A dog is a very slow runner...");
    }

    @Override
    public void sleep() {
        System.out.println("A dog is sleeping...Zzzzzz...");
    }
}
