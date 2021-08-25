package springboot_bootstrap_312.webapp.service;


import springboot_bootstrap_312.webapp.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public void deleteUser(Long id);
}
