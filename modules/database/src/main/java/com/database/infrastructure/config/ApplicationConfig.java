package com.database.infrastructure.config;

import com.database.application.ports.input.UserAppService;
import com.database.application.ports.input.VehicleAppService;
import com.database.application.ports.output.UserRepositoryPort;
import com.database.application.ports.output.VehicleApiPort;
import com.database.application.services.UserAppServiceImp;
import com.database.application.services.VehicleAppAppServiceImp;
import com.database.domain.user.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserAppService userAppService(UserRepositoryPort userRepositoryPort, UserValidator userValidator) {
        return new UserAppServiceImp(userRepositoryPort, userValidator);
    }

    /*@Bean
    public UserRepositoryPort userRepository(UserJpaRepository jpaRepository, UserEntityMapper mapper) {
        return new UserRepositoryPortAdapter(jpaRepository, mapper);
    }*/

    @Bean
    public UserValidator userDomainService() {
        return new UserValidator();
    }

    /*@Bean
    public VehicleApiPort vehicleApiPort(RestTemplate restTemplate, VehicleResponseMapper vehicleResponseMapper){
        return new VehicleRestAdapter(restTemplate, vehicleResponseMapper);
    }*/

    @Bean
    public VehicleAppService vehicleServicePort(VehicleApiPort vehicleApiPort){
        return new VehicleAppAppServiceImp(vehicleApiPort);
    }
}
