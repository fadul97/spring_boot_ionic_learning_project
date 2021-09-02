package com.leonardofadul.springboot.ionic.learning.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardofadul.springboot.ionic.learning.project.domain.BankBilletPayment;
import com.leonardofadul.springboot.ionic.learning.project.domain.CardPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(BankBilletPayment.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}
