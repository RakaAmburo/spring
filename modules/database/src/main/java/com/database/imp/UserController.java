package com.database.imp;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Value("${test.value}")
    private String valor;

    @GetMapping("/var")
    public String getVar() {
        return valor;
    }

    @GetMapping("/fm")
    public List<VehicleDTO> getFromMongo() {
        return userService.getVehicles();
    }

    @CircuitBreaker(name = "userController", fallbackMethod = "fallback")
    @Retry(name = "userController")
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<List<User>> fallback(Exception  e) {
        if (e instanceof CallNotPermittedException) {
            userService.countFallbacks();
            return ResponseEntity.status(503).body(null);
        }
        return ResponseEntity.status(500).body(null);
    }

    /*@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(500).body("Error after retries");
    }*/

    @PostMapping
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

/*
http://localhost:8080/h2-console/login.jsp?jsessionid=d8fd0dbf40474ccc692472cf8491ce61
http://localhost:8080/users
$body = '{"name":"test","age":1}'
        curl -Method POST -Uri http://localhost:8080/users -Body $body -ContentType 'application/json'

        $body = '{"name":"nuevo","surname":"test","age":99}'
curl -Method PUT -Uri http://localhost:8080/users/1 -Body $body -ContentType 'application/json' -UseBasicParsing

        curl -Method DELETE -Uri http://localhost:8080/users/1


        refresh
        curl -Method POST http://localhost:8080/actuator/refresh -Headers @{"Content-Type"="application/json"} -UseBasicParsing

        https://adoptium.net/temurin/releases/?version=21





//todos
RECORDS puede remplacer lombok en controllers?


 Usa repo privado con token o SSH. para config server

 2023.0.3
 set('springCloudVersion', "2025.1.0")

 @Import({FluxLogger.class})

 subre todo version

 analizar codigo mejorar warnings

 java run rapido creo que era con un java espacial

        */
