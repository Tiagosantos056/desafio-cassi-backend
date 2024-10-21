package com.example.desafiocassi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Desafio CASSI, gerenciar produtos", version = "1", description = "API desenvolvida para desafio Desenvolvedor Back-end JAVA na empresa CASSI."))
@SpringBootApplication
public class DesafioCassiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioCassiApplication.class, args);
    }

}
