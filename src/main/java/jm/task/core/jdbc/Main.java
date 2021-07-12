package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Paul", "Anderson", (byte) 44);
        userService.saveUser("Mike", "Myers", (byte) 35);
        userService.saveUser("Harry", "Potter", (byte) 16);
        userService.saveUser("Steven", "Gerrard", (byte) 24);

        System.out.println(userService.getAllUsers().toString());

        userService.removeUserById(4);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
