package assignment29;

import assignment28.Account;

class SavingsAccount extends Account {

    private float interest;

    SavingsAccount() {
        setName("SavingsAccount");
        setBalance(0);
        this.interest = 5;
    }

    @Override
    public void withdraw(int withdrawnAmount) {
        float tempBalance = getBalance() - withdrawnAmount;
        //setBalance((int) (getBalance() - withdrawnAmount));
        if (tempBalance < 0) {  //was getBalance()
            System.out.println(String.format(
                    "Attempted to withdraw %d, which exceeds the account's balance. Max of %.2f withdrawn",
                    withdrawnAmount,
                    getBalance()
            ));
            setBalance(0);
        }
    }

    float getAnnualInterest(float balance, float interest) {
        return (balance / 100) * interest;
    }

    void setInterest(float interest) {
        this.interest = interest;
    }

    float getInterest() {
        return interest;
    }
}
