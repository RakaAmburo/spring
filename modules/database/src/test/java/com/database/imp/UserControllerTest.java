package com.database.imp;

import com.database.application.ports.input.UserAppService;
import com.database.infrastructure.persistance.user.UserRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "eureka.client.enabled=false",
        "spring.cloud.discovery.enabled=false",
        "resilience4j.circuitbreaker.instances.userController.slidingWindowSize=10",
        "resilience4j.circuitbreaker.instances.userController.failureRateThreshold=50",
        "resilience4j.circuitbreaker.instances.userController.waitDurationInOpenState=1s",
        "resilience4j.circuitbreaker.instances.userController.minimumNumberOfCalls=5",
        "resilience4j.retry.instances.userController.maxAttempts=2",
        "resilience4j.retry.instances.userController.waitDuration=1s",
        "resilience4j.retry.instances.userController.exponentialBackoffMultiplier=2",
        "resilience4j.circuitbreaker.circuit-breaker-aspect-order=1",
        "resilience4j.retry.retry-aspect-order=2"
})
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserAppService userAppService;

    @MockBean
    UserServiceAux userServiceAux;

    @Test
    void testRetryYCircuitBreaker() throws Exception {
        when(userAppService.getAllUsers()).thenThrow(new RuntimeException());

        for (int i = 0; i < 5; i++) {
            mockMvc.perform(get("/users"))
                    .andExpect(status().is5xxServerError());
        }

        mockMvc.perform(get("/users"))
                .andExpect(status().isServiceUnavailable());

        verify(userAppService, times(10)).getAllUsers();
        verify(userServiceAux, times(1)).countFallbacks();

        Thread.sleep(1000);

        reset(userAppService);
        when(userAppService.getAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());

        verify(userAppService, times(1)).getAllUsers();
    }
}