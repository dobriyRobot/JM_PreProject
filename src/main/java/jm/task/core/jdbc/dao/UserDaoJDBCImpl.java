package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static UserDaoJDBCImpl daoJDBC;

    public static UserDaoJDBCImpl getInstance() {
        if (daoJDBC == null) {
            daoJDBC = new UserDaoJDBCImpl();
        }
        return daoJDBC;
    }

    private UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement st = Util.getConnection().createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS Users (id bigint primary key auto_increment, name varchar(250), lastname varchar(250), age smallint);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = Util.getConnection().createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS Users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String myStr = "INSERT INTO Users (name, lastname, age) VALUES (?, ?, ?);";
        try (PreparedStatement pSt = Util.getConnection().prepareStatement(myStr)) {
            pSt.setString(1, name);
            pSt.setString(2, lastName);
            pSt.setInt(3, age);
            pSt.executeUpdate();
            System.out.println(name + " был добавлен в БД!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String myStr = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement pSt = Util.getConnection().prepareStatement(myStr)) {
            pSt.setInt(1, (int) id);
            pSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement st = Util.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM Users;");
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong(1));
                    user.setName(rs.getString(2));
                    user.setLastName(rs.getString(3));
                    user.setAge(rs.getByte(4));
                    userList.add(user);
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement st = Util.getConnection().createStatement()) {
            st.executeUpdate("TRUNCATE TABLE Users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
