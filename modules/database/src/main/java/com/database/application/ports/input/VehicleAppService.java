package com.database.application.ports.input;

import com.database.domain.vehicle.Vehicle;

import java.util.List;

public interface VehicleAppService {
    List<Vehicle> getVehicles();

    void countFallbacks();
}
