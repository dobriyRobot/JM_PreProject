package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public User getUser(Long id);
    public void deleteUser(Long id);
    public Role getRoleByName(String name);
}
