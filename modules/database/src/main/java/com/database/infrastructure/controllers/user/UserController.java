package com.database.infrastructure.controllers.user;

import com.database.application.ports.input.UserAppService;
import com.database.domain.user.User;
import com.database.imp.UserServiceAux;
import com.database.imp.VehicleDTO;
import com.database.infrastructure.persistance.user.UserEntity;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRequestMapper userRequestMapper;
    @Autowired
    private UserServiceAux userServiceAux;

    @Autowired
    UserAppService userAppService;

    @Value("${test.value}")
    private String valor;

    @GetMapping("/var")
    public String getVar() {
        return valor;
    }

    @GetMapping("/fm")
    public List<VehicleDTO> getFromMongo() {
        return userServiceAux.getVehicles();
    }

    @CircuitBreaker(name = "userController", fallbackMethod = "fallback")
    @Retry(name = "userController")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = userAppService.getAllUsers();
        //users.stream().forEach(u-> System.out.println(u.toString()));
        return ResponseEntity.ok(users.stream().map(userRequestMapper::toResponse).toList());
    }

    public ResponseEntity<List<UserEntity>> fallback(Exception e) {
        if (e instanceof CallNotPermittedException) {
            userServiceAux.countFallbacks();
            return ResponseEntity.status(503).body(null);
        }
        return ResponseEntity.status(500).body(null);
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        User user = userRequestMapper.toDomain(userRequest);
        User createdUser = userAppService.createUser(user);
        return userRequestMapper.toResponse(createdUser);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User user = userRequestMapper.toDomain(userRequest);
        User updatedUser = userAppService.updateUser(user);
        return userRequestMapper.toResponse(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userAppService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}

/*

 2023.0.3
 set('springCloudVersion', "2025.1.0")

 @Import({FluxLogger.class})

 subre todo version

 analizar codigo mejorar warnings

 java run rapido creo que era con un java espacial

        */
