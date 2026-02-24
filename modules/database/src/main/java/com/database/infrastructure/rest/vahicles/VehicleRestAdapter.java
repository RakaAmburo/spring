package com.database.infrastructure.rest.vahicles;

import com.database.application.ports.output.VehicleApiPort;
import com.database.domain.vehicle.Vehicle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VehicleRestAdapter implements VehicleApiPort {

    private final RestTemplate restTemplate;
    private final VehicleResponseMapper vehicleResponseMapper;

    public VehicleRestAdapter(RestTemplate restTemplate, VehicleResponseMapper vehicleResponseMapper) {
        this.restTemplate = restTemplate;
        this.vehicleResponseMapper = vehicleResponseMapper;

    }
    @Override
    public List<Vehicle> getVehicles() {
        return restTemplate.exchange(
                "http://mongo/vehicles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VehicleResponse>>() {
                }
        ).getBody().stream().map(vehicleResponseMapper::toDomain).toList();
    }
}
