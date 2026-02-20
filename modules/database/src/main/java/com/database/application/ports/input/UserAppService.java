package com.database.application.ports.input;

import com.database.domain.user.User;

import java.util.List;

public interface UserAppService {
    User createUser(User user);
    User updateUser(User user);
    void removeUser(Long id);
    List<User> getAllUsers();
}

   /* User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);*/