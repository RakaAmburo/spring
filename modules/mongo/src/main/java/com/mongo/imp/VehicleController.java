package com.mongo.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    @Value("${test.value}")
    private String valor;

    @GetMapping("/var")
    public String getVar() {
        return valor;
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable String id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id);
        return repository.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}

/*
http://localhost:8080/vehicles
$body = '{"licensePlate":"ABC123","description":"Auto rojo"}'
curl -Method POST -Uri http://localhost:8081/vehicles -Body $body -ContentType 'application/json' -UseBasicParsing

spring.mongodb.embedded.storage.database-dir=${user.home}/mongodb-data
spring.mongodb.embedded.storage.oplog-size=0
spring.mongodb.embedded.version=4.4.30
spring.data.mongodb.port=27017

refresh
curl -Method POST http://localhost:8080/actuator/refresh -Headers @{"Content-Type"="application/json"}

*/
