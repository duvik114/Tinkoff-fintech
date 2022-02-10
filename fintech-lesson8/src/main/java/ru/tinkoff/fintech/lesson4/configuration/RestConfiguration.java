package ru.tinkoff.fintech.lesson4.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jackson -> jackson.serializationInclusion(Include.NON_NULL);
    }
}

