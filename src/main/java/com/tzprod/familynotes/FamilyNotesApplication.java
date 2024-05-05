package com.tzprod.familynotes;

import com.tzprod.familynotes.controller.FamilyNotesController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FamilyNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyNotesApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v1/api/topic").allowedOrigins("http://localhost:8080");
                registry.addMapping("v1/api/note").allowedOrigins("http://localhost:8080");
            }
        };
    }

}
