package com.abnambro.project.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class TestRecipeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.from(RecipeServiceApplication::main)
                .with(TestConfigs.class)
                .run(args);
    }
}
