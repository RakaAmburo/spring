package com.database.infrastructure.persistance.user;

import com.database.application.ports.output.UserRepository;
import com.database.domain.user.User;

import java.util.List;

public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository jpaRepository;
    private final UserEntityMapper mapper;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository, UserEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<User> findAll() {
        //jpaRepository.findAll().stream().forEach(u-> System.out.println(u.toString()));
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public User update(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity updatedEntity = jpaRepository.save(entity);
        return mapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
