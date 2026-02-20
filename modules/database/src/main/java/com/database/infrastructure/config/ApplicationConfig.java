package com.database.infrastructure.config;

import com.database.application.ports.input.UserAppService;
import com.database.application.ports.output.UserRepository;
import com.database.application.services.UserAppServiceImp;
import com.database.domain.user.UserDomainService;
import com.database.infrastructure.persistance.user.UserEntityMapper;
import com.database.infrastructure.persistance.user.UserJpaRepository;
import com.database.infrastructure.persistance.user.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserAppService userAppService(UserRepository userRepository, UserDomainService userDomainService) {
        return new UserAppServiceImp(userRepository, userDomainService);
    }

    @Bean
    public UserRepository userRepository(UserJpaRepository jpaRepository, UserEntityMapper mapper) {
        return new UserRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainService();
    }
}
