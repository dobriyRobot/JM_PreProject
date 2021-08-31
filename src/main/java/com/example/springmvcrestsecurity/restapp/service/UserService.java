package com.example.springmvcrestsecurity.restapp.service;



import com.example.springmvcrestsecurity.restapp.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public User getUser(Long id);

    public boolean isAdmin(String email);
}
