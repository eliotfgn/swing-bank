package models;

public class SavingAccount extends Account{
    private double interest;

    public SavingAccount(Long id, User owner, double balance) {
        super(id, owner, balance);
        this.interest = 0.4;
    }

    public SavingAccount() {}

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
