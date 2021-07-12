package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/funky_db", "root", "Nthvbyfnjh1");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
        return connection;
    }
}
