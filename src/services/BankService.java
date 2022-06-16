package services;

import database.DatabaseConnector;
import models.Account;
import models.Deposit;
import models.Transaction;
import models.Withdraw;

public class BankService {
    private static BankService instance;
    private final DatabaseConnector dataConnector = DatabaseConnector.getInstance();

    private BankService() {}

    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    public boolean deposit(long accountId, double amount) {
        Account account = dataConnector.findAccount(accountId);
        Transaction deposit = new Deposit(amount, account);
        dataConnector.updateAccountBalance(accountId, account.getBalance()+amount);

        return dataConnector.addTransaction(deposit);
    }

    public boolean withdraw(long accountId, double amount, String label) {
        Account account = dataConnector.findAccount(accountId);
        Transaction withdraw = new Withdraw(amount, account, label);
        dataConnector.updateAccountBalance(accountId, account.getBalance()-amount);

        return dataConnector.addTransaction(withdraw);
    }
}
