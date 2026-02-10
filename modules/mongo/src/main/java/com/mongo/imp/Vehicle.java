package com.mongo.imp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "vehicles")
@Data
public class Vehicle {
    @Id
    private String id;
    private String licensePlate;
    private String description;
}