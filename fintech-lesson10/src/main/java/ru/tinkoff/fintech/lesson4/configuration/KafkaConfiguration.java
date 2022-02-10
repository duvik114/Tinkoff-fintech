package ru.tinkoff.fintech.lesson4.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.tinkoff.fintech.lesson4.model.KafkaStudent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    @Bean
    public ProducerFactory<String, KafkaStudent> producerFactoryStudents() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "sulky-01.srvs.cloudkafka.com:9094," +
                "sulky-02.srvs.cloudkafka.com:9094,sulky-03.srvs.cloudkafka.com:9094");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put("sasl.mechanism", "SCRAM-SHA-256");
        properties.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required" +
                " username='000m6ke2' password='KJ2ZbB7B5NmjCq803E1Abuli132eH1dy';");
        properties.put("security.protocol", "SASL_SSL");
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, KafkaStudent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactoryStudents());
    }
}
