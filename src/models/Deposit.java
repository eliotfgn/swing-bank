package models;

import java.util.Date;

public class Deposit extends Transaction{
    public Deposit() {
    }

    public Deposit(double amount, Account account) {
        super(amount, account);
    }
}
