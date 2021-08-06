package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public User showUserUseModelAndSeries(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT u FROM User u LEFT OUTER JOIN Car c ON u.car = c WHERE c.model = :model AND c.series = :series");
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      return query.getSingleResult();
   }

   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User showUser(Long id) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User where id=:id");
      query.setParameter("id", id);
      return query.getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
