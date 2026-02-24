package com.database.application.ports.output;

import com.database.domain.user.User;

import java.util.List;

public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
    User update(User user);
    void delete(Long id);
}
