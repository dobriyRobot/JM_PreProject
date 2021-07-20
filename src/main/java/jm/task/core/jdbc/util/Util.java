package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;


public class Util {
    final static String url = "jdbc:mysql://localhost:3306/funky_db?useSSL=false";
    final static String userName = "root";
    final static String password = "Nthvbyfnjh1";

//============================================H I B E R N A T E========================================================
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", url);
            properties.setProperty("hibernate.connection.username", userName);
            properties.setProperty("hibernate.connection.password", password);
            properties.setProperty("hibernate.connection.pool_size", "1");
            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.current_session_context_class", "thread");
            properties.setProperty("show_sql", "true");

            sessionFactory = new Configuration().addProperties(properties).
                    addAnnotatedClass(User.class).buildSessionFactory();
        }
        return sessionFactory;
    }

//==========================================J D B C================================================================
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                System.out.println("Unable to connect");
            }
        } else {
            try {
                if (connection.isClosed()){
                    try {
                        connection = DriverManager.getConnection(url, userName, password);
                    } catch (SQLException e) {
                        System.out.println("Unable to connect");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
