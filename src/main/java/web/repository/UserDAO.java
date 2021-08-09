package web.repository;

import web.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public User getUser(Long id);
    public void deleteUser(Long id);
    public User findByUsername(String username);
}
