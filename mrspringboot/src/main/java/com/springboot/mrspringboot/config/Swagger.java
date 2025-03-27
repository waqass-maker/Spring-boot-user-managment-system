package com.springboot.mrspringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI  mycustoom(){
        return  new OpenAPI().info(new Info().title("Journel APP API").description("by waqas")).servers(List.of(new Server().url("http://localhost:8080").description("local")));
    }
}
