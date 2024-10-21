package com.example.desafiocassi.config;


import com.example.desafiocassi.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    @Primary
    public boolean instanceDB() {

        if(value.equals("create")) {
            this.dbService.startDB();
        }
        return false;
    }
}
