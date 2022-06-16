package services;

import database.DatabaseConnector;
import exceptions.UserNotFoundException;
import models.User;

import java.sql.SQLException;

public class UserService {
    private static UserService instance;
    private final DatabaseConnector dataConnector = DatabaseConnector.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    public boolean register(User user) {
        return dataConnector.saveUser(user);
    }

    public boolean login(String email, String password) {
        User user = null;
        try {
            user = dataConnector.findUser(email);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return user.getPassword().equals(password);
        }

    }
}
