package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public User showUserUseModelAndSeries(Car car) {
      return userDao.showUserUseModelAndSeries(car);
   }

   @Transactional
   @Override
   public void add(User user, Car car) {
      userDao.add(user, car);
   }

   @Transactional
   @Override
   public User showUser(Long id) {
      return userDao.showUser(id);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

}
