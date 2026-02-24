package com.database.infrastructure.rest.vahicles;


import com.database.domain.vehicle.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleResponseMapper {
    Vehicle toDomain(VehicleResponse vehicleResponse);
}
