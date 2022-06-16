package models;

public class CurrentAccount extends Account{
    public CurrentAccount() {

    }

    public CurrentAccount(Long id, User owner, double balance) {
        super(id, owner, balance);
    }
}
