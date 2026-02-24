package com.database.application.services;

import com.database.application.ports.input.UserAppService;
import com.database.application.ports.output.UserRepositoryPort;
import com.database.domain.user.User;
import com.database.domain.user.UserValidator;


import java.util.List;

public class UserAppServiceImp implements UserAppService {

    final UserRepositoryPort userRepository;
    final UserValidator userValidator;

    public UserAppServiceImp(UserRepositoryPort userRepositoryPort, UserValidator userValidator) {
        this.userRepository = userRepositoryPort;
        this.userValidator = userValidator;
    }

    @Override
    public User createUser(User user) {
        //reglas de negocio
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.update(user);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
