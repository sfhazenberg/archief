public class Assignment22 {

    public static void main(String[] args) {
        Employee Pieter = new Employee(1, "Pieter", "Post", 2500);
        Pieter.raiseSalary(15);
        Pieter.printOverview();

        Employee Henry = new Employee(2, "Henry", "Henriksen", 1250);
        Henry.raiseSalary(23);
        Henry.printOverview();
    }
}
