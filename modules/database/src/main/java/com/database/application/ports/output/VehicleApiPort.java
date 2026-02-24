package com.database.application.ports.output;

import com.database.domain.vehicle.Vehicle;

import java.util.List;

public interface VehicleApiPort {
    List<Vehicle> getVehicles();
}
