package com.prosig.blog;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class BlogIntegrationTest {

    protected static final PostgreSQLContainer DATABASE_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.2"))
            .withDatabaseName("prosig")
            .withUsername("prosig")
            .withPassword("prosig");

    static {
        DATABASE_CONTAINER.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DATABASE_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", DATABASE_CONTAINER::getUsername);
        registry.add("spring.datasource.password", DATABASE_CONTAINER::getPassword);
    }
}
