package com.database.application.services;

import com.database.application.ports.input.VehicleAppService;
import com.database.application.ports.output.VehicleApiPort;
import com.database.domain.vehicle.Vehicle;

import java.util.List;

public class VehicleAppAppServiceImp implements VehicleAppService {

    private final VehicleApiPort vehicleApiPort;

    public VehicleAppAppServiceImp(VehicleApiPort vehicleApiPort) {
        this.vehicleApiPort = vehicleApiPort;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleApiPort.getVehicles();
    }

    @Override
    public void countFallbacks() {

    }
}
