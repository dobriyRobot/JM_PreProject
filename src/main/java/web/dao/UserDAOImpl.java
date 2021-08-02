package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select c from User c", User.class);
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select c from User c where c.id =: id", User.class);
        query.setParameter("id", id);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUser(id));
    }
}
