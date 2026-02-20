package com.database.application.services;

import com.database.application.ports.input.UserAppService;
import com.database.application.ports.output.UserRepository;
import com.database.domain.user.User;
import com.database.domain.user.UserDomainService;


import java.util.List;

public class UserAppServiceImp implements UserAppService {

    final UserRepository userRepository;
    final UserDomainService userDomainService;

    public UserAppServiceImp(UserRepository userRepository, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
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
