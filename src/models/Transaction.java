package models;

import java.util.Date;

public abstract class Transaction {
    protected Long id;
    protected Date date;
    protected double amount;
    protected Account account;

    public Transaction(Long id, Date date, double amount, Account account) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public Transaction(double amount, Account account) {
        this.amount = amount;
        this.account = account;
        this.date = new Date();
    }

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
