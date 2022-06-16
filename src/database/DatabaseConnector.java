package database;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private static DatabaseConnector instance;
    private Connection connection;
    private PreparedStatement statement;

    private DatabaseConnector() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/bank", "swinguser", "swingtest");
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            try {
                instance = new DatabaseConnector();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    public boolean saveUser(User user) {
        try {
            String query = "INSERT INTO user(firstname,lastname,email,password) VALUES(?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public User findUser(String email) throws SQLException {
        String query = "SELECT * " +
                "FROM user " +
                "WHERE email=(?)";
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        statement.setString(1, email);
        ResultSet result = statement.executeQuery();
        result.next();

        User user = new User();
        user.setId(result.getLong("id"));
        user.setEmail(result.getString("email"));
        user.setFirstname(result.getString("firstname"));
        user.setLastname(result.getString("lastname"));
        user.setPassword(result.getString("password"));

        result.close();

        return user;
    }

    public User findUser(int id) throws SQLException {
        String query = "SELECT * " +
                "FROM user " +
                "WHERE id=(?)";
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        result.next();

        User user = new User();
        user.setId(result.getLong("id"));
        user.setEmail(result.getString("email"));
        user.setFirstname(result.getString("firstname"));
        user.setLastname(result.getString("lastname"));
        user.setPassword(result.getString("password"));

        result.close();

        return user;
    }

    public boolean createAccount(Account account, String type) {
        String query = "INSERT INTO account(owner_id,balance,type)" +
                " VALUES(?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, (int) account.getOwner().getId());
            statement.setDouble(2, account.getBalance());
            statement.setString(3, type);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
            return false;
        }

        return true;
    }

    public List<Account> findUserAccounts(User user) {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * " +
                "FROM account " +
                "WHERE owner_id=(?)";
        ResultSet result;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, (int) user.getId());
            result = statement.executeQuery();
            while (result.next()) {
                Account account;
                String type = result.getString("type");
                if (type.equals("CURRENT")) {
                    account = new CurrentAccount();
                }
                else {
                    account = new SavingAccount();
                }
                account.setBalance(result.getDouble("balance"));
                account.setOwner(findUser(result.getInt("id")));
                account.setId(result.getLong("id"));
                accounts.add(account);
            }
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }

        return accounts;
    }

    public boolean updateAccountBalance(long id, double newBalance) {
        String query = "UPDATE account " +
                "set balance=(?) " +
                "where id=(?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(2, id);
            statement.setDouble(1, newBalance);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean addTransaction(Transaction transaction) {
        String type = "", label="";
        if (transaction instanceof Deposit) {
            type = "DEPOSIT";
            label = "";
        } else if (transaction instanceof Withdraw) {
            type = "WITHDRAW";
            label = ((Withdraw) transaction).getLabel();
        }

        String query = "INSERT INTO transaction(amount, label, date, type, account_id) " +
                "VALUES (?,?,?,?,?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setDouble(1, transaction.getAmount());
            statement.setString(2, label);
            statement.setDate(3, Date.valueOf(transaction.getDate().toString()));
            statement.setString(4, type);
            statement.setLong(5, transaction.getAccount().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Account findAccount(long id) {
        Account account = null;
        String query = "SELECT * " +
                "FROM account " +
                "WHERE id=(?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String type = result.getString("type");
                long accountId = result.getLong("id");
                double balance = result.getDouble("balance");
                User owner = findUser(result.getInt("owner_id"));

                if (type.equals("CURRENT")) {
                    account = new CurrentAccount(accountId, owner, balance);
                } else {
                    account = new SavingAccount(accountId, owner, balance);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return account;
    }
}
