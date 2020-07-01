class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    Employee(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    String getName() {
        return firstName + " " + lastName;
    }

    int getAnnualSalary() {
        return salary * 12;
    }

    void raiseSalary(int percent) {
        salary = (salary / 100) * (100 + percent);
    }

    void printOverview() {
        System.out.println("Employee " + id + ", " + getName() + ", earns €" + getAnnualSalary() + " annually.");
    }
}
