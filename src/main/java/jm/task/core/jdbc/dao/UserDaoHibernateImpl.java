package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id bigint primary key auto_increment, " +
                    "name varchar(250), lastname varchar(250), age smallint);").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            userList = (List<User>) session.createQuery("FROM User").list();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE User").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
