package ru.tinkoff.fintech.spring.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.tinkoff.fintech.spring.demo.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
// Set profile (for example add in vm options "-Dspring.profiles.active=dev"
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties")
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper jackson() {
        return new ObjectMapper();
    }
}
