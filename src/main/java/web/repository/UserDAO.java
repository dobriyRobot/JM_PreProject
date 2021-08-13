package web.repository;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public User getUser(Long id);
    public void deleteUser(Long id);
    public User findByUsername(String username);
    public Role getRoleByName(String name);
}
