package models;

import java.util.Date;

public abstract class Transaction {
    protected Long id;
    protected Date date;
    protected double amount;

    public Transaction(Long id, Date date, double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(double amount) {
        this.amount = amount;
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


}
