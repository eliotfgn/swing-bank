package models;

public class Withdraw extends Transaction{
    private String label;
    public Withdraw() {}

    public Withdraw(double amount, Account account, String label) {
        super(amount, account);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
