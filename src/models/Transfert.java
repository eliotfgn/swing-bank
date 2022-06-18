package models;

import java.util.Date;

public class Transfert extends Transaction{
    private String label;
    private Account receiver;

    public Transfert(double amount, String label, Account receiver) {
        super(amount, receiver);
        this.label = label;
        this.receiver = receiver;
    }

    public Transfert(String label, Account receiver) {
        this.label = label;
        this.receiver = receiver;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
}
