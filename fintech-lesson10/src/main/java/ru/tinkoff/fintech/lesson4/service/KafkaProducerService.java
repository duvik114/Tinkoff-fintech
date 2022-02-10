package ru.tinkoff.fintech.lesson4.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.fintech.lesson4.model.KafkaStudent;
import ru.tinkoff.fintech.lesson4.model.Student;

import java.util.UUID;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaStudent> kafkaTemplate;
    private final String topic = "000m6ke2-fintech-out";

    public KafkaProducerService(KafkaTemplate<String, KafkaStudent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Student student) {
        kafkaTemplate.send(topic, new KafkaStudent(student, false));
    }

    public void sendMessage(UUID id) {
        kafkaTemplate.send(topic, new KafkaStudent(id, true));
    }
}
