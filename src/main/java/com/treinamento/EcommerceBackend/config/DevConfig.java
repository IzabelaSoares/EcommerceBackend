package com.treinamento.EcommerceBackend.config;

import com.treinamento.EcommerceBackend.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DatabaseService databaseService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instanciateDatabase() throws ParseException {
        if(!"create".equals(strategy)){
            return false;
        }
        else{
            databaseService.instantiateTestDatabase();
            return true;
        }
    }

}