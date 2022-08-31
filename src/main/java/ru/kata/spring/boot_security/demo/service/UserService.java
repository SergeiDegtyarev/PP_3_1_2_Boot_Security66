package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User findById(Integer id);
    void updateUser(User user);

    void deleteUser(Integer id);

    User findUserName (String name);

    List<User> getAllUsers();
}