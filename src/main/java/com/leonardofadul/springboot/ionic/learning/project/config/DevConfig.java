package com.leonardofadul.springboot.ionic.learning.project.config;

import com.leonardofadul.springboot.ionic.learning.project.services.DBService;
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
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String databaseStrategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if(!databaseStrategy.equals("create")){
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }
}
