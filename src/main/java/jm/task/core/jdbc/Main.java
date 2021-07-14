package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Paul", "Anderson", (byte) 44);
        userService.saveUser("Mike", "Myers", (byte) 35);
        userService.saveUser("Harry", "Potter", (byte) 16);
        userService.saveUser("Steven", "Gerrard", (byte) 24);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("User: " + user);
        }

        userService.removeUserById(4);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
