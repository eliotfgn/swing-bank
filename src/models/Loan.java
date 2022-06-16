package models;

public class Loan {
    private User creditor;
    private Account creditorAccount;
    private double amount;
    private boolean isReimboursed;

    public Loan(User creditor, Account creditorAccount, double amount) {
        this.creditor = creditor;
        this.creditorAccount = creditorAccount;
        this.amount = amount;
        this.isReimboursed = false;
    }

    public User getCreditor() {
        return creditor;
    }

    public void setCreditor(User creditor) {
        this.creditor = creditor;
    }

    public Account getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(Account creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isReimboursed() {
        return isReimboursed;
    }

    public void setReimboursed(boolean reimboursed) {
        isReimboursed = reimboursed;
    }
}
