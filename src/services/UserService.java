package services;

import database.DatabaseConnector;
import exceptions.UserNotFoundException;
import models.User;

import java.sql.SQLException;

public class UserService {
    private final DatabaseConnector dataConnector = DatabaseConnector.getInstance();
    public boolean register(User user) {
        if(dataConnector.saveUser(user)) {
            return true;
        } else {
            return false;
        }
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
