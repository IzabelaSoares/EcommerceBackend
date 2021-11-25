package com.treinamento.EcommerceBackend.config;

import com.treinamento.EcommerceBackend.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DatabaseService databaseService;

    @Bean
    public boolean instanciateDatabase() throws ParseException {
        databaseService.instantiateTestDatabase();
        return true;
    }

}
