package com.database.infrastructure.rest.vahicles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
    private String id;
    private String licensePlate;
    private String description;
}
