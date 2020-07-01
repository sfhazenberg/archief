package assignment29;

public class Assignment29 {

    public static void main(String[] args) {
        System.out.println("--1-----------------------------------------");
        SavingsAccount raboSpaarrekening = new SavingsAccount();
        raboSpaarrekening.deposit(5000);
        raboSpaarrekening.printOverview();
        System.out.println(String.format(
                "Annual interest of %s is %.2f percent and yields %.2f",
                raboSpaarrekening.getName(),
                raboSpaarrekening.getInterest(),
                raboSpaarrekening.getAnnualInterest(raboSpaarrekening.getBalance(),raboSpaarrekening.getInterest())
        ));

        System.out.println("--2-----------------------------------------");
        raboSpaarrekening.withdraw(30000);
        raboSpaarrekening.printOverview();

        System.out.println("--3-----------------------------------------");
        SavingsAccount abnSpaarrekening = new SavingsAccount();
        abnSpaarrekening.deposit(1000);
        abnSpaarrekening.withdraw(2000);
        abnSpaarrekening.printOverview();
    }

}
