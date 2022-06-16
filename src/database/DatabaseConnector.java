package database;

import models.Account;
import models.CurrentAccount;
import models.SavingAccount;
import models.User;

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

    public List<Account> findUserAccount(User user) {
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
}
