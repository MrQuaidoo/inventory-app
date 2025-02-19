package com.inventory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

/**
 * Main entry point for the Inventory Management System.
 * Bootstraps the Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(InventoryApplication.class);
        Map<String, Object> defaultProperties = new HashMap<>();

        // Set default application properties, such as the server port
        defaultProperties.put("server.port", 8080);
        app.setDefaultProperties(defaultProperties);

        // Run the Spring Boot application
        app.run(args);
    }
}