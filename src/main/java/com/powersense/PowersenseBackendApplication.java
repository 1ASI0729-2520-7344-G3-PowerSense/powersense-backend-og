package com.powersense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class PowersenseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowersenseBackendApplication.class, args);
        System.out.println("=================================================");
        System.out.println("PowerSense Backend API is running!");
        System.out.println("API Base URL: http://localhost:8080/");
        System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("=================================================");
    }

}
