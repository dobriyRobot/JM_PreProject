package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static UserDaoHibernateImpl daoHibernate;

    public static UserDaoHibernateImpl getInstance() {
        if (daoHibernate == null) {
            daoHibernate = new UserDaoHibernateImpl();
        }
        return daoHibernate;
    }

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id bigint primary key auto_increment, name varchar(250), lastname varchar(250), age smallint);").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("drop table if exists user").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().getCurrentSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> userList = (List<User>) session.createQuery("from User").list();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
    }
}
