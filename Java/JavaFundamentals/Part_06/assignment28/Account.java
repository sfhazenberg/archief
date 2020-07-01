package assignment28;

public class Account {

    private String name;
    private int balance;

    public void printOverview() {
        System.out.println(String.format(
                "Overview: name = %s, balance = %d",
                name,
                balance
        ));
    }

    public void withdraw(int withdrawnAmount) {
        balance -= withdrawnAmount;
    }

    public void deposit(int depositedAmount) {
        balance += depositedAmount;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setBalance(int balance) {
        this.balance = balance;
    }
}
