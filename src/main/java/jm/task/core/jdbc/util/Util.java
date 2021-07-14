package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static Connection connection;
    final static String url = "jdbc:mysql://localhost:3306/funky_db?useSSL=false";
    final static String userName = "root";
    final static String password = "Nthvbyfnjh1";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                System.out.println("Unable to connect");
            }
        }
        return connection;
    }
}
