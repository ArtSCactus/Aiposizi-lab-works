package com.bsuir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.bsuir")
@EnableJpaRepositories(basePackages = "com.bsuir")
public class RestServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(RestServerStarter.class, args);
    }

}
