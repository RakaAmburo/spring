package com.database.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public List<VehicleDTO> getVehicles() {
        return restTemplate.exchange(
                "http://mongo/vehicles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VehicleDTO>>() {
                }
        ).getBody();
    }
}
